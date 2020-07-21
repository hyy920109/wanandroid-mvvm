package com.hyy.data_api_coroutine.repository

import com.hyy.data_api_coroutine.model.Banner
import com.hyy.data_api_coroutine.model.HomeArticleList

interface HomeRepository {
    suspend fun requestHomeArticles(page: Int) : HomeArticleList

    suspend fun fetchHomeBanner() : List<Banner>
}