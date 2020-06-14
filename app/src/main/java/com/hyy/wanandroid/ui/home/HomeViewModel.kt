package com.hyy.wanandroid.ui.home

import android.util.Log
import androidx.lifecycle.*
import com.hyy.wanandroid.data.RESPONSE_CODE_SUCCESS
import com.hyy.wanandroid.data.RESPONSE_CODE_UNKNOWN
import com.hyy.wanandroid.data.RESPONSE_MSG_UNKNOWN
import com.hyy.wanandroid.data.ResultData
import com.hyy.wanandroid.data.bean.HomeArticleList
import com.hyy.wanandroid.data.network.RequestStatus
import com.hyy.wanandroid.data.network.request
import com.hyy.wanandroid.data.repository.HomeRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class HomeViewModel(private val homeRepository: HomeRepository) : ViewModel() {

    private val _homeArticles = MutableLiveData<ResultData<HomeArticleList>>()

    val homeArticleList : MutableLiveData<ResultData<HomeArticleList>>
    get() = _homeArticles

    private val pageChannel: Channel<Int> = Channel<Int>()
    
    init {
        Log.d(TAG, "init: ${homeArticleList.value?.data?.size}")
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
        Log.d(TAG, "observePageChanged: ")
        viewModelScope.launch{
            pageChannel.receiveAsFlow()
                .onEach {page->
                    Log.d(TAG, "receivePage --> $page")
                    fetchHomeArticles(page)
                }
                .launchIn(this)
        }
    }

    private fun fetchHomeArticles(page: Int) {
        viewModelScope.launch {
            request {
                homeRepository.requestHomeArticles(page)
            }.collect {
                _homeArticles.postValue(it)
            }
        }
    }

    fun loadMore() {
        viewModelScope.launch {
            pageChannel.send((pageChannel.poll() ?: 0)+1)
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