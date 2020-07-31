package com.hyy.data_rxjava

import com.hyy.data_api_rxjava.model.Article
import com.hyy.data_api_rxjava.repository.ILocalArticleRepository
import com.hyy.data_rxjava.mapper.toDomain
import com.hyy.data_rxjava.mapper.toEntity
import io.reactivex.Flowable

/**
 *Create by hyy on 2020/7/31
 */
class LocalArticleRepository(private val store: Store) : ILocalArticleRepository {

    override fun addFavorite(article: Article) {
        val articleEntity = article.toEntity()
        articleEntity.favorite = true
        store.localProvider().insertArticle(articleEntity)
    }

    override fun addHistory(article: Article) {
        val articleEntity = article.toEntity()
        articleEntity.history = true
        articleEntity.readTime = (System.currentTimeMillis() / 1000L).toInt()
        store.localProvider()
        store.localProvider().insertArticle(articleEntity)
    }

    override fun getFavoriteArticles(): Flowable<List<Article>> {
        return store.localProvider().getAllFavoriteArticles()
            .map {
                it.map { articleEntity ->  articleEntity.toDomain() }
            }
    }

    override fun getHistoryArticles(): Flowable<List<Article>> {
        return store.localProvider().getAllHistoryArticles()
            .map {
                it.map { articleEntity ->  articleEntity.toDomain() }
            }
    }
}