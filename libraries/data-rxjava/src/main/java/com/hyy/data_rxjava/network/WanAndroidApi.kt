package com.hyy.data_rxjava.network

import com.hyy.data_rxjava.network.model.BannerModel
import com.hyy.data_rxjava.network.model.BaseModel
import com.hyy.data_rxjava.network.model.HomeArticleListModel
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface WanAndroidApi {

    @GET("article/list/{page}/json")
    fun getArticle(@Path("page") page: Int = 0) : Single<BaseModel<HomeArticleListModel>>

    @GET("banner/json")
    fun getHomeBanner() : Single<BaseModel<List<BannerModel>>>
}