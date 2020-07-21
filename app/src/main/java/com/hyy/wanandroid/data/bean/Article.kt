package com.hyy.wanandroid.data.bean


data class Article(
    val author: String,
    val chapterId: Int,
    val chapterName: String,
    val collect: Boolean,
    val fresh: Boolean,
    val id: Int,
    val link: String,
    val niceDate: String,
    val niceShareDate: String,
    val publishTime: Long,
    val shareDate: Long,
    val shareUser: String,
    val superChapterName: String,
    val title: String,
    val userId: Int,
    val visible: Int,
    val zan: Int
)