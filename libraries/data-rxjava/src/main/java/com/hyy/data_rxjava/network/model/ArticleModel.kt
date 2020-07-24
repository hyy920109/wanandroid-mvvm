package com.hyy.data_rxjava.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ArticleModel(
    @Json(name = "author")
    val author: String,
    @Json(name = "chapterId")
    val chapterId: Int,
    @Json(name = "chapterName")
    val chapterName: String,
    @Json(name = "collect")
    val collect: Boolean,
    @Json(name = "fresh")
    val fresh: Boolean,
    @Json(name = "id")
    val id: Int,
    @Json(name = "link")
    val link: String,
    @Json(name = "niceDate")
    val niceDate: String,
    @Json(name = "niceShareDate")
    val niceShareDate: String,
    @Json(name = "publishTime")
    val publishTime: Long,
    @Json(name = "shareDate")
    val shareDate: Long,
    @Json(name = "shareUser")
    val shareUser: String,
    @Json(name = "superChapterName")
    val superChapterName: String,
    @Json(name = "title")
    val title: String,
    @Json(name = "userId")
    val userId: Int,
    @Json(name = "visible")
    val visible: Int,
    @Json(name = "zan")
    val zan: Int
)