package com.hyy.wanandroid.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HomeArticleListModel(
    @Json(name = "curPage")
    val curPage: Int,
    @Json(name = "datas")
    val articleList: List<ArticleModel>?,
    @Json(name = "offset")
    val offset: Int,
    @Json(name = "over")
    val over: Boolean,
    @Json(name = "pageCount")
    val pageCount: Int,
    @Json(name = "size")
    val size: Int,
    @Json(name = "total")
    val total: Int
)