package com.hyy.data_coroutine

import com.hyy.data_api_coroutine.model.Banner
import com.hyy.data_api_coroutine.model.HomeArticleList
import com.hyy.data_api_coroutine.repository.HomeRepository


class HomeRepositoryImpl(private val store: Store) : HomeRepository {

    override suspend fun requestHomeArticles(page: Int):HomeArticleList {
        return store.remoteProvider().getHomeArticle(page)
    }

    override suspend fun fetchHomeBanner(): List<Banner> {
        return store.remoteProvider().fetchHomeBanner()
    }
}