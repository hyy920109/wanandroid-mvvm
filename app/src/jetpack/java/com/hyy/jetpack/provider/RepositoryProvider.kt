package com.hyy.jetpack.provider

import android.content.Context
import com.hyy.data_api_coroutine.repository.HomeRepository
import com.hyy.data_coroutine.HomeRepositoryImpl
import com.hyy.data_coroutine.Store

object RepositoryProvider {

    private lateinit var store: Store

    @JvmStatic
    fun inject(context: Context) {
        store = Store.getInstance(context)
    }

    @JvmStatic
    fun getHomeRepository() : HomeRepository = HomeRepositoryImpl(store = store)
}