package com.hyy.data_rxjava.database

import android.content.Context
import androidx.room.Room

/**
 *Create by hyy on 2020/7/30
 */
class DBClient(val context: Context) {

     private val db: AppDatabase = Room.databaseBuilder(
        context, AppDatabase::class.java,
        "${context.packageName}.db"
    ).build()

    fun getDB() = db
}