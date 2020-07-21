package com.hyy.data_api_coroutine.model

data class HomeArticleList(
    val curPage: Int,
    val offset: Int,
    val articleList: List<Article>,
    val over: Boolean,
    val pageCount: Int,
    val size: Int,
    val total: Int
)