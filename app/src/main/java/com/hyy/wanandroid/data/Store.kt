package com.hyy.wanandroid.data

import android.content.Context
import com.hyy.wanandroid.data.network.BASE_URL
import com.hyy.wanandroid.data.network.NetworkClient
import com.hyy.wanandroid.data.network.RemoteProvider

class Store private constructor(context: Context){

    private val remoteProvider : RemoteProvider by lazy {
        RemoteProvider(NetworkClient(BASE_URL, WanAndroidApi::class.java))
    }

    init {

    }

    fun remoteProvider() = remoteProvider

    companion object {
        @Volatile
        private var instance: Store? = null
        fun getInstance(context: Context) : Store = instance ?: synchronized(this) {
            instance ?: Store(context)
        }
    }
}