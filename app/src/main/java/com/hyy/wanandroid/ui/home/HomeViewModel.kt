package com.hyy.wanandroid.ui.home

import androidx.lifecycle.*
import com.hyy.wanandroid.data.ResultData
import com.hyy.wanandroid.data.bean.Article
import com.hyy.wanandroid.data.bean.HomeArticleList
import com.hyy.wanandroid.data.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class HomeViewModel(private val homeRepository: HomeRepository) : ViewModel() {

    private val _homeArticles = MediatorLiveData<ResultData<HomeArticleList>>()

    val homeArticleList : LiveData<ResultData<HomeArticleList>>
    get() = _homeArticles

    private val curPage = MutableLiveData<Int>().apply {
        value = 0
    }

    fun fetchHomeArticles() {
        viewModelScope.launch {

            curPage.value?.apply {
                val homeArticles = homeRepository.requestHomeArticles(this)


            }

        }
    }

    fun loadMore() {

    }
}