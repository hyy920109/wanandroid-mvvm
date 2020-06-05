package com.hyy.wanandroid.data

import com.hyy.wanandroid.data.model.BaseModel
import com.hyy.wanandroid.data.model.HomeArticleListModel
import retrofit2.http.GET
import retrofit2.http.Path

interface WanAndroidApi {

    @GET("article/list/{page}/json")
    suspend fun getArticle(@Path("page") page: Int = 0) : BaseModel<HomeArticleListModel>
}