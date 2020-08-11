package com.hyy.data_rxjava.network

import com.hyy.data_rxjava.network.model.*
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface WanAndroidApi {

    @GET("article/list/{page}/json")
    fun getArticle(@Path("page") page: Int = 0) : Single<BaseModel<HomeArticleListModel>>

    @GET("banner/json")
    fun getHomeBanner() : Single<BaseModel<List<BannerModel>>>

    @POST("user/login")
    fun loginCall(@Query("username") username:String,@Query("password") password:String):Single<BaseModel<LoginModel>>

    @POST("user/register")
    fun registerCall(@Query("username") username:String,@Query("password") password:String,@Query("repassword") repassword:String):Single<BaseModel<RegisterModel>>
}