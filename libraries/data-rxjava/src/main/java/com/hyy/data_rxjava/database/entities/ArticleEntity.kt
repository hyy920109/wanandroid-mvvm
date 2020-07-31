package com.hyy.data_rxjava.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 *Create by hyy on 2020/7/30
 */
@Entity(tableName = "Article", primaryKeys = ["id"])
data class ArticleEntity(
    val author: String,
    //自定义列名  默认以字段名称为列名
    @ColumnInfo(name = "chapter_id") val chapterId: Int,
    @ColumnInfo(name = "chapter_name") val chapterName: String,
    val collect: Boolean,
    val fresh: Boolean,
    val id: Int,
    val link: String,
    @ColumnInfo(name = "nice_date") var niceDate: String,
    @ColumnInfo(name = "nice_share_date") var niceShareDate: String,
    @ColumnInfo(name = "publish_time") var publishTime: Long,
    @ColumnInfo(name = "share_date") var shareDate: Long,
    @ColumnInfo(name = "share_user") val shareUser: String,
    @ColumnInfo(name = "super_chapter_name") val superChapterName: String,
    val title: String,
    @ColumnInfo(name = "user_id") val userId: Int,
    var visible: Int,
    val zan: Int,
    var favorite: Boolean,//是否是收藏
    var history: Boolean,//是否是浏览过得文章
    @ColumnInfo(name = "read_time") var readTime: Int = 0//浏览时间
)