package com.hyy.wanandroid.data

import com.hyy.wanandroid.data.bean.Article
import com.hyy.wanandroid.data.bean.Banner
import com.hyy.wanandroid.data.bean.HomeArticleList
import com.hyy.wanandroid.data.model.ArticleModel
import com.hyy.wanandroid.data.model.BannerModel
import com.hyy.wanandroid.data.model.HomeArticleListModel

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