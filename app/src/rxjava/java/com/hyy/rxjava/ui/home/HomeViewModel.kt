package com.hyy.rxjava.ui.home

import androidx.lifecycle.*
import com.hyy.data_api_rxjava.model.Banner
import com.hyy.data_api_rxjava.model.HomeArticleList
import com.hyy.data_api_rxjava.repository.HomeRepository
import com.hyy.rxjava.base.BaseViewModel
import com.hyy.rxjava.data_base.ResultData
import com.hyy.rxjava.ext.resolve
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.functions.BiFunction
import io.reactivex.rxjava3.subjects.BehaviorSubject

class HomeViewModel(private val homeRepository: HomeRepository) : BaseViewModel() {

    private val _homeData = BehaviorSubject.create<ResultData<HomeData>>()
    private val _moreArticles = BehaviorSubject.create<ResultData<HomeArticleList>>()

    val homeData: Observable<ResultData<HomeData>>
        get() = _homeData.hide()

    val moreArticles: Observable<ResultData<HomeArticleList>>
        get() = _moreArticles.hide()
    private var _currPage = 0
    private var _pageCount = 0

    init {
        fetchHomeData(_currPage)
    }

    private fun loadMoreArticle(page: Int) {
    }

    private fun fetchHomeData(page: Int) {
        _homeData.onNext(ResultData.start())
        addDisposable {
            Single.zip(
                homeRepository.requestHomeArticles(page),
                homeRepository.fetchHomeBanner(),
                BiFunction { homeArticleList: HomeArticleList, banners: List<Banner> ->
                    if (homeArticleList.articleList.isEmpty() or banners.isEmpty()) {
                        ResultData.empty()
                    } else {
                        _currPage = homeArticleList.curPage
                        _pageCount = homeArticleList.pageCount
                        ResultData.success(HomeData(banners, homeArticleList))
                    }
                })
                .doOnError {
                    _homeData.onNext(
                        ResultData.error(
                            it.resolve().errorMsg,
                            it.resolve().errorCode
                        )
                    )
                }.doOnSuccess {
                    _homeData.onNext(it)
                }.subscribe()
        }
    }

    fun loadMore() {
        if (_currPage == _pageCount) {
            _moreArticles.onNext(ResultData.empty())
        }
        addDisposable {
            homeRepository.requestHomeArticles(_currPage)
                .doOnSuccess {
                    if (it.articleList.isEmpty()) {
                        _moreArticles.onNext(ResultData.empty())
                    }else {
                        _moreArticles.onNext(ResultData.success(it))
                    }
                }
                .doOnError { _moreArticles.onNext(ResultData.error(it.resolve().errorMsg, it.resolve().errorCode)) }
                .subscribe()
        }
    }

    override fun onCleared() {
        super.onCleared()
    }

    companion object {
        const val TAG = "HomeViewModel"
    }
}