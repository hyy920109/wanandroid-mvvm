package com.hyy.data_rxjava.database

import com.hyy.data_rxjava.database.entities.ArticleEntity
import io.reactivex.Flowable

/**
 * local data provider
 *Create by hyy on 2020/7/30
 */
class LocalProvider(private val dbClient: DBClient) {

    //favorite api
    fun getAllFavoriteArticles(): Flowable<List<ArticleEntity>> {
        return dbClient.getDB().favoriteDao().getAllFavoriteArticle()
    }

    fun addFavoriteArticle(articleEntity: ArticleEntity) {
        dbClient.getDB().favoriteDao().addFavoriteArticle(articleEntity)
    }

    fun deleteFavoriteArticle(id: Int) {
        dbClient.getDB().favoriteDao().removeArticleFromFavorite(id = id)
    }
}