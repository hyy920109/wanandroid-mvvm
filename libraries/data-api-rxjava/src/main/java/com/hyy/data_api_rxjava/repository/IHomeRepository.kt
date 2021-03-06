package com.hyy.data_api_rxjava.repository

import com.hyy.data_api_rxjava.model.Article
import com.hyy.data_api_rxjava.model.Banner
import com.hyy.data_api_rxjava.model.HomeArticleList
import io.reactivex.Flowable
import io.reactivex.Single


interface IHomeRepository {
    fun requestHomeArticles(page: Int) : Single<HomeArticleList>

    fun fetchHomeBanner() : Single<List<Banner>>


}