package com.hyy.data_rxjava.mapper

import com.hyy.data_api_rxjava.model.Article
import com.hyy.data_api_rxjava.model.Banner
import com.hyy.data_api_rxjava.model.HomeArticleList
import com.hyy.data_api_rxjava.model.LoginBean
import com.hyy.data_rxjava.network.model.ArticleModel
import com.hyy.data_rxjava.network.model.BannerModel
import com.hyy.data_rxjava.network.model.HomeArticleListModel
import com.hyy.data_rxjava.network.model.LoginModel

/**
 * model to data
 */
fun ArticleModel.toDomain() = Article(
    author,
    chapterId,
    chapterName,
    collect,
    fresh,
    id,
    link,
    niceDate,
    niceShareDate,
    publishTime,
    shareDate,
    shareUser,
    superChapterName,
    title,
    userId,
    visible,
    zan
)

fun HomeArticleListModel.toDomain() =
    HomeArticleList(
        curPage,
        offset,
        articleList.map { it.toDomain() },
        over,
        pageCount,
        size,
        total
    )

fun BannerModel.toDomain() =
    Banner(
        desc,
        id,
        imagePath,
        isVisible,
        order,
        title,
        type,
        url
    )

fun LoginModel.toDomain()=
    LoginBean(
        admin,coinCount, nickname, id, token
    )