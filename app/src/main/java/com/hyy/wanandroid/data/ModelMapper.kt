package com.hyy.wanandroid.data

import com.hyy.wanandroid.data.bean.Article
import com.hyy.wanandroid.data.bean.Banner
import com.hyy.wanandroid.data.bean.HomeArticleList
import com.hyy.wanandroid.data.model.ArticleModel
import com.hyy.wanandroid.data.model.BannerModel
import com.hyy.wanandroid.data.model.HomeArticleListModel

fun ArticleModel.toDomain() = Article(
    apkLink,
    audit,
    author,
    canEdit,
    chapterId,
    chapterName,
    collect,
    courseId,
    desc,
    descMd,
    envelopePic,
    fresh,
    id,
    link,
    niceDate,
    niceShareDate,
    origin,
    prefix,
    projectLink,
    publishTime,
    selfVisible,
    shareDate,
    shareUser,
    superChapterId,
    superChapterName,
    tags,
    title,
    type,
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