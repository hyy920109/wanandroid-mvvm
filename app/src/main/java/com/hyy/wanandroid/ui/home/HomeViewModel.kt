package com.hyy.wanandroid.ui.home

import android.util.Log
import androidx.lifecycle.*
import com.hyy.wanandroid.data.ResultData
import com.hyy.wanandroid.data.bean.HomeArticleList
import com.hyy.wanandroid.data.network.RequestStatus
import com.hyy.wanandroid.data.network.resolveError
import com.hyy.wanandroid.data.network.simpleRequest
import com.hyy.wanandroid.data.repository.HomeRepository
import com.hyy.wanandroid.ext.addSource
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import java.io.IOException

@ExperimentalCoroutinesApi
class HomeViewModel(private val homeRepository: HomeRepository) : ViewModel() {

    private val _homeArticles = MediatorLiveData<ResultData<HomeArticleList>>()

    val homeArticleList: MediatorLiveData<ResultData<HomeArticleList>>
        get() = _homeArticles

    private val _homeData = MediatorLiveData<ResultData<HomeData>>()

    val homeData: MediatorLiveData<ResultData<HomeData>>
        get() = _homeData

    private val pageChannel: Channel<Int> = Channel<Int>()

    init {
        observePageChanged()
        initPage()
    }

    private fun initPage() {
        viewModelScope.launch {
            pageChannel.send(0)
        }
    }

    @ExperimentalCoroutinesApi
    private fun observePageChanged() {
        viewModelScope.launch {
            pageChannel.receiveAsFlow()
                .onEach { page ->
                    if (page == 0) {
                        fetchHomeData(page)
                    }
                }
                .launchIn(this)
        }
    }

    private fun fetchHomeData(page: Int) {
        viewModelScope.launch {

//            val simpleRequest = simpleRequest {
//                homeRepository.requestHomeArticles(page)
//            }
//            _homeArticles.addSource(simpleRequest)
            val homeDataResource = liveData {
                try {
                    emit(ResultData.start())
                    val banners = homeRepository.fetchHomeBanner()
                    val articles = homeRepository.requestHomeArticles(page)
                    if (banners.status == RequestStatus.EMPTY || articles.status == RequestStatus.EMPTY) {
                        emit(ResultData.empty())
                    } else {
                        emit(ResultData.success(HomeData(banners.data!!, articles.data!!)))
                    }
                } catch (e: Throwable) {
                    val error = resolveError(e)
                    emit(ResultData.error(error.errorMsg, error.errorCode))
                }
            }
            _homeData.addSource(homeDataResource)

        }
    }

    fun loadMore() {
        viewModelScope.launch {
            pageChannel.send((pageChannel.poll() ?: 0) + 1)
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