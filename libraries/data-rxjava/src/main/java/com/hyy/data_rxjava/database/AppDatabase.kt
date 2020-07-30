package com.hyy.data_rxjava.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hyy.data_rxjava.database.dao.FavoriteDao
import com.hyy.data_rxjava.database.entities.ArticleEntity

/**
 *Create by hyy on 2020/7/27
 */
@Database(entities = [ArticleEntity::class],exportSchema = true, version = 1)
abstract class AppDatabase: RoomDatabase(){
    //我的收藏
    abstract fun favoriteDao(): FavoriteDao

}