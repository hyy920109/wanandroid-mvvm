package com.hyy.wanandroid.data.repository.impl

import androidx.lifecycle.LiveData
import com.hyy.wanandroid.data.ResultData
import com.hyy.wanandroid.data.Store
import com.hyy.wanandroid.data.bean.Article
import com.hyy.wanandroid.data.bean.Banner
import com.hyy.wanandroid.data.bean.HomeArticleList
import com.hyy.wanandroid.data.repository.HomeRepository

class HomeRepositoryImpl(private val store: Store) : HomeRepository {

    override suspend fun requestHomeArticles(page: Int): ResultData<HomeArticleList> {
        return store.remoteProvider().getHomeArticle(page)
    }

    override suspend fun fetchHomeBanner(): ResultData<List<Banner>> {
        return store.remoteProvider().fetchHomeBanner()
    }
}