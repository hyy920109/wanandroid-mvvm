package com.hyy.data_api_rxjava.repository

import com.hyy.data_api_rxjava.model.Article
import io.reactivex.Flowable

/**
 *Create by hyy on 2020/7/31
 */
interface ILocalArticleRepository {

    fun addFavorite(article: Article)

    fun addHistory(article: Article)

    fun getFavoriteArticles(): Flowable<List<Article>>

    fun getHistoryArticles(): Flowable<List<Article>>
}