package com.hyy.data_rxjava.database.dao

import androidx.room.*
import com.hyy.data_rxjava.database.entities.ArticleEntity
import io.reactivex.Flowable

/**
 *Create by hyy on 2020/7/30
 */

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addFavoriteArticle(articleEntity: ArticleEntity)

    @Query("SELECT * FROM Article WHERE favorite = 1")
    fun getAllFavoriteArticle(): Flowable<List<ArticleEntity>>

    @Query("DELETE FROM Article WHERE id=:id")
    fun removeArticleFromFavorite(id: Int)

}