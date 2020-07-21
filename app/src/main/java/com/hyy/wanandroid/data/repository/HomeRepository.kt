package com.hyy.wanandroid.data.repository

import com.hyy.wanandroid.data.ResultData
import com.hyy.wanandroid.data.bean.Banner
import com.hyy.wanandroid.data.bean.HomeArticleList

interface HomeRepository {

    suspend fun requestHomeArticles(page: Int) : ResultData<HomeArticleList>

    suspend fun fetchHomeBanner() : ResultData<List<Banner>>
}