package com.hyy.wanandroid.ui.home

import android.util.Log
import androidx.lifecycle.*
import com.hyy.wanandroid.data.RESPONSE_CODE_SUCCESS
import com.hyy.wanandroid.data.RESPONSE_CODE_UNKNOWN
import com.hyy.wanandroid.data.RESPONSE_MSG_UNKNOWN
import com.hyy.wanandroid.data.ResultData
import com.hyy.wanandroid.data.bean.HomeArticleList
import com.hyy.wanandroid.data.network.RequestStatus
import com.hyy.wanandroid.data.repository.HomeRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel(private val homeRepository: HomeRepository) : ViewModel() {

    private val _homeArticles = MediatorLiveData<ResultData<HomeArticleList>>()

    val homeArticleList : LiveData<ResultData<HomeArticleList>>
    get() = _homeArticles

    private val pageChannel: Channel<Int> = Channel<Int>()

    init {
       observePageChanged()
       initPage()
    }

    private fun initPage() {
        viewModelScope.launch {
            Log.d(TAG, "send --> 0")

            pageChannel.send(0)
        }
    }

    @ExperimentalCoroutinesApi
    private fun observePageChanged() {
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
            flow {
                emit(ResultData.start())
                val data = homeRepository.requestHomeArticles(page)
                emit(data)
            }.catch {e->
                emit(ResultData(
                    data = null,
                    status = RequestStatus.ERROR,
                    errorMsg = e.message ?: RESPONSE_MSG_UNKNOWN,
                    errorCode = RESPONSE_CODE_UNKNOWN
                ))
            }.onEach {
                Log.d(TAG, "data status -->${it.status}")
            }.collect{

            }

        }
    }

    fun loadMore() {
        viewModelScope.launch {
            pageChannel.send(pageChannel.poll() ?: 0)
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