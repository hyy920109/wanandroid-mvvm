package com.hyy.data_rxjava.database.dao

import androidx.room.*
import com.hyy.data_rxjava.database.entities.ArticleEntity
import io.reactivex.Flowable
import io.reactivex.Single

/**
 *Create by hyy on 2020/7/30
 */

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticle(articleEntity: ArticleEntity)

    @Query("SELECT * FROM Article WHERE favorite = 1")
    fun getAllFavoriteArticles(): Flowable<List<ArticleEntity>>

    @Query("SELECT * FROM Article WHERE history = 1 ORDER BY read_time DESC")
    fun getAllHistoryArticles(): Flowable<List<ArticleEntity>>

    @Query("DELETE FROM Article WHERE id=:id")
    fun removeArticleFromFavorite(id: Int)

}