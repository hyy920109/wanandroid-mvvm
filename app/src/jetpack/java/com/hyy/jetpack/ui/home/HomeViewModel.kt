package com.hyy.jetpack.ui.home

import android.util.Log
import androidx.lifecycle.*
import com.hyy.wanandroid.data.ExceptionWrapper
import com.hyy.wanandroid.data.ResultData
import com.hyy.wanandroid.data.bean.Banner
import com.hyy.wanandroid.data.bean.HomeArticleList
import com.hyy.wanandroid.data.network.*
import com.hyy.wanandroid.data.repository.HomeRepository
import com.hyy.wanandroid.ext.addSource
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import okhttp3.internal.wait
import kotlin.coroutines.CoroutineContext

@ExperimentalCoroutinesApi
class HomeViewModel(private val homeRepository: HomeRepository) : ViewModel() {

    private val _homeArticles = MediatorLiveData<ResultData<HomeArticleList>>()

    val homeArticleList: MediatorLiveData<ResultData<HomeArticleList>>
        get() = _homeArticles

    private val _homeData = MediatorLiveData<ResultData<HomeData>>()

    val homeData: MediatorLiveData<ResultData<HomeData>>
        get() = _homeData

    private val pageChannel: Channel<Int> = Channel<Int>()

    private var _currPage = 0

    init {
        observePageChanged()
        initPage()
    }

    private fun initPage() {
        viewModelScope.launch {
            pageChannel.send(_currPage)
        }
    }

    @ExperimentalCoroutinesApi
    private fun observePageChanged() {
        viewModelScope.launch {
            pageChannel.receiveAsFlow()
                .onEach { page ->
                    if (page == 0) {
                        fetchHomeData(page)
                    } else {
                        loadMoreArticle(page)
                    }
                }
                .launchIn(this)
        }
    }

    private fun loadMoreArticle(page: Int) {
        viewModelScope.launch {
            val simpleRequest = simpleRequest {
                homeRepository.requestHomeArticles(page)
            }
            _homeArticles.addSource(simpleRequest)
        }
    }

    private fun fetchHomeData(page: Int) {

        viewModelScope.launch {

            val homeDataResource = liveData {

                try {
                    emit(ResultData.start())
                    //异步调用的话 需要用viewModelScope.async{}
                    val bannersFuture = viewModelScope.async { homeRepository.fetchHomeBanner() }
                    val articlesFuture =
                        viewModelScope.async { homeRepository.requestHomeArticles(page) }
                    val banners = bannersFuture.await()
                    val articles = articlesFuture.await()
                    if (banners.status == RequestStatus.EMPTY || articles.status == RequestStatus.EMPTY) {
                        emit(ResultData.empty())
                    } else {
                        emit(ResultData.success(HomeData(banners.data!!, articles.data!!)))
                    }
                } catch (e: Exception) {
                    val error = resolveError(e)
                    emit(ResultData.error(error.errorMsg, error.errorCode))
                }
            }
            _homeData.addSource(homeDataResource)

        }
    }

    fun loadMore() {
        viewModelScope.launch {
            pageChannel.send(++_currPage)
        }
    }

    override fun onCleared() {
        pageChannel.close()
        super.onCleared()
    }

    companion object {
        const val TAG = "HomeViewModel"
    }
}