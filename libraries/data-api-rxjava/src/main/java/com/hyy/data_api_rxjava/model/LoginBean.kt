package com.hyy.data_api_rxjava.model

/**
 * Created by heCunCun on 2020/8/11
 */
data class LoginBean(
    val admin: Boolean,
    val coinCount: Int,
    val nickname: String,
    val id: String,
    val token: String
)