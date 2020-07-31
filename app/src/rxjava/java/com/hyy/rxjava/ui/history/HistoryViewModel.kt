package com.hyy.rxjava.ui.history

import com.hyy.data_api_rxjava.model.Article
import com.hyy.data_api_rxjava.repository.ILocalArticleRepository
import com.hyy.rxjava.base.BaseViewModel
import com.hyy.rxjava.data_base.ResultData
import com.hyy.rxjava.ext.resolve
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject

/**
 *Create by hyy on 2020/7/31
 */
class HistoryViewModel(private val localArticleRepo: ILocalArticleRepository) : BaseViewModel() {

    private val _historyArticles = BehaviorSubject.create<ResultData<List<Article>>>()

    val historyArticles: Observable<ResultData<List<Article>>>
        get() = _historyArticles.hide()

    init {
        observeHistoryArticles()
    }

    private fun observeHistoryArticles() {
        _historyArticles.onNext(ResultData.start())
        addDisposable {
            localArticleRepo.getHistoryArticles()
                .subscribeOn(Schedulers.io())
                .doOnNext {
                    if (it.isEmpty()) {
                        _historyArticles.onNext(ResultData.empty())
                    }else {
                        _historyArticles.onNext(ResultData.success(it))
                    }
                }
                .doOnError {
                    _historyArticles.onNext(
                        ResultData.error(
                            it.resolve().errorMsg,
                            it.resolve().errorCode
                        )
                    )
                }
                .subscribe()
        }
    }
}