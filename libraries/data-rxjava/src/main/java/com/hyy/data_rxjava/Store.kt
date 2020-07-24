package com.hyy.data_rxjava

import android.content.Context
import com.hyy.data_rxjava.network.NetworkClient
import com.hyy.data_rxjava.network.RemoteProvider
import com.hyy.data_rxjava.network.WanAndroidApi

class Store private constructor(context: Context){

    private val remoteProvider : RemoteProvider by lazy {
        RemoteProvider(NetworkClient(BASE_URL, WanAndroidApi::class.java))
    }

    fun remoteProvider() = remoteProvider

    companion object {
        @Volatile
        private var instance: Store? = null
        fun getInstance(context: Context) : Store = instance ?: synchronized(this) {
            instance ?: Store(context).also { instance = it }
        }
    }
}