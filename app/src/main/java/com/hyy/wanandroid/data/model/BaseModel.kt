package com.hyy.wanandroid.data.model


import com.squareup.moshi.Json

data class BaseModel<out T>(
    @Json(name = "data")
    val data: T,
    @Json(name = "errorCode")
    val errorCode: Int,
    @Json(name = "errorMsg")
    val errorMsg: String
)