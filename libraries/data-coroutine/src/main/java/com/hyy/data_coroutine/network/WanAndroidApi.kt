package com.hyy.data_coroutine.network

import com.hyy.data_coroutine.network.model.BannerModel
import com.hyy.data_coroutine.network.model.BaseModel
import com.hyy.data_coroutine.network.model.HomeArticleListModel
import retrofit2.http.GET
import retrofit2.http.Path

interface WanAndroidApi {

    @GET("article/list/{page}/json")
    suspend fun getArticle(@Path("page") page: Int = 0) : BaseModel<HomeArticleListModel>

    @GET("banner/json")
    suspend fun getHomeBanner() : BaseModel<List<BannerModel>>
}