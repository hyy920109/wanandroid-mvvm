package com.hyy.data_rxjava.mapper

import com.hyy.data_api_rxjava.model.Article
import com.hyy.data_rxjava.database.entities.ArticleEntity

/**
 * entity to Model
 * Model to entity
 * data to entity
 * entity to data
 *Create by hyy on 2020/7/30
 */
fun Article.toEntity() = ArticleEntity(
    author, chapterId, chapterName, collect, fresh,
    id, link, niceDate, niceShareDate, publishTime, shareDate, shareUser, superChapterName, title,
    userId, visible, zan, favorite = false, history = false
)

fun ArticleEntity.toDomain() = Article(
    author, chapterId, chapterName, collect, fresh, id,
    link, niceDate, niceShareDate, publishTime, shareDate, shareUser,
    superChapterName, title, userId, visible, zan
)