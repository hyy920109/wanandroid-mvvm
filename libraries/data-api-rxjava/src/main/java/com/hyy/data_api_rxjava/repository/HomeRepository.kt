package com.hyy.data_api_rxjava.repository

import com.hyy.data_api_rxjava.model.Banner
import com.hyy.data_api_rxjava.model.HomeArticleList
import io.reactivex.rxjava3.core.Single


interface HomeRepository {
    fun requestHomeArticles(page: Int) : Single<HomeArticleList>

    fun fetchHomeBanner() : Single<List<Banner>>
}