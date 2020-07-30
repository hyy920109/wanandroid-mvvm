package com.hyy.data_rxjava

import android.content.Context
import com.hyy.data_rxjava.database.DBClient
import com.hyy.data_rxjava.database.LocalProvider
import com.hyy.data_rxjava.network.NetworkClient
import com.hyy.data_rxjava.network.RemoteProvider
import com.hyy.data_rxjava.network.WanAndroidApi

class Store private constructor(context: Context){

    private val remoteProvider : RemoteProvider by lazy {
        RemoteProvider(NetworkClient(BASE_URL, WanAndroidApi::class.java))
    }

    private val localProvider: LocalProvider by lazy {
        LocalProvider(DBClient(context))
    }

    fun remoteProvider() = remoteProvider

    fun localProvider() = localProvider

    companion object {
        @Volatile
        private var instance: Store? = null
        fun getInstance(context: Context) : Store = instance ?: synchronized(this) {
            instance ?: Store(context).also { instance = it }
        }
    }
}