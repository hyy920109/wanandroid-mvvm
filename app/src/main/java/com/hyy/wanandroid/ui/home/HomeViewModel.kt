package com.hyy.wanandroid.ui.home

import androidx.lifecycle.*
import com.hyy.wanandroid.data.ResultData
import com.hyy.wanandroid.data.bean.HomeArticleList
import com.hyy.wanandroid.data.network.request
import com.hyy.wanandroid.data.repository.HomeRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class HomeViewModel(private val homeRepository: HomeRepository) : ViewModel() {

    private val _homeArticles = MutableLiveData<ResultData<HomeArticleList>>()

    val homeArticleList: MutableLiveData<ResultData<HomeArticleList>>
        get() = _homeArticles

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