package com.hyy.data_rxjava.database

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

/**
 *Create by hyy on 2020/7/30
 */
class DBClient(val context: Context) {

    //升级步骤
    //1.写sql语句
    //2.addMigrations
    //3.创建新表或者更新原有表字段
    //4，修改room versionCode
    private val migration1To2 = object : Migration(1,2){
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE Article ADD COLUMN `read_time` INTEGER NOT NUll default 0")
        }
    }

     private val db: AppDatabase = Room.databaseBuilder(
        context, AppDatabase::class.java,
        "${context.packageName}.db"
    ).addMigrations(migration1To2)
         .build()

    fun getDB() = db
}