package com.hyy.data_rxjava.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by heCunCun on 2020/8/11
 */
@JsonClass(generateAdapter = true)
data class LoginModel(
//{
//    "admin": false,
//    "chapterTops": [],
//    "coinCount": 0,
//    "collectIds": [],
//    "email": "",
//    "icon": "",
//    "id": 73361,
//    "nickname": "hecuncun",
//    "password": "",
//    "publicName": "hecuncun",
//    "token": "",
//    "type": 0,
//    "username": "hecuncun"
//}
    @Json(name = "admin")
    val admin :Boolean,
    @Json(name =  "coinCount")
    val coinCount:Int,
    @Json(name = "nickname")
    val nickname:String,
    @Json(name = "id")
    val id:String,
    @Json(name = "token")
    val token:String
)