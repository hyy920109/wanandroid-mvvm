package com.hyy.jetpack.ui.home

import androidx.lifecycle.*
import com.hyy.data_api_coroutine.model.HomeArticleList
import com.hyy.data_api_coroutine.repository.HomeRepository
import com.hyy.jetpack.ext.simpleRequest
import com.hyy.jetpack.data_base.ResultData
import com.hyy.jetpack.ext.resolveError
import com.hyy.jetpack.ext.zipRequest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*

@ExperimentalCoroutinesApi
class HomeViewModel(private val homeRepository: HomeRepository) : ViewModel() {

    private val _homeArticles = MutableLiveData<ResultData<HomeArticleList>>()

    val homeArticleList: LiveData<ResultData<HomeArticleList>>
        get() = _homeArticles

    private val _homeData = MutableLiveData<ResultData<HomeData>>()

    val homeData: LiveData<ResultData<HomeData>>
        get() = _homeData

    private val pageChannel: Channel<Int> = Channel<Int>()

    private var currPage = 0

    private val pageFlow = MutableStateFlow(currPage)

    init {
        observePageChanged()
    }

    @ExperimentalCoroutinesApi
    private fun observePageChanged() {
        pageFlow.mapLatest { page ->
            if (page == 0) {
                fetchHomeData(page)
            } else {
                loadMoreArticle(page)
            }
        }.launchIn(viewModelScope)
    }

    private fun loadMoreArticle(page: Int) {
        simpleRequest({
            homeRepository.requestHomeArticles(page)
        }, successBlock = { homeArticle ->
            _homeArticles.value = ResultData.success(homeArticle)
        }, errorBlock = { e ->
            val error = resolveError(e)
            _homeArticles.value = ResultData.error(error.errorMsg, error.errorCode)
        })
    }

    private fun fetchHomeData(page: Int) {
        zipRequest({ homeRepository.fetchHomeBanner() },
            { homeRepository.requestHomeArticles(page) },
            loadingBlock = {
                _homeData.value = ResultData.start()
            },
            successBlock = { banners, homeArticles ->
                if (homeArticles.articleList.isEmpty() || banners.isEmpty()) {
                    _homeData.value = ResultData.empty()
                } else {
                    _homeData.value = ResultData.success(HomeData(banners, homeArticles))
                }
            },
            errorBlock = { error ->
                println("$TAG --->$error")
                _homeData.value = ResultData.error(error.errorMsg, error.errorCode)
            })
    }

    @ExperimentalCoroutinesApi
    fun loadMore() {
        pageFlow.value = ++currPage
    }

    override fun onCleared() {
        pageChannel.close()
        super.onCleared()
    }

    companion object {
        const val TAG = "HomeViewModel"
    }
}