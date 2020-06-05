package com.hyy.wanandroid.data

import android.content.Context
import com.hyy.wanandroid.data.repository.HomeRepository
import com.hyy.wanandroid.data.repository.impl.HomeRepositoryImpl

object RepositoryProvider {

    private lateinit var store: Store

    @JvmStatic
    fun inject(context: Context) {
        store = Store.getInstance(context)
    }

    @JvmStatic
    fun getHomeRepository() : HomeRepository = HomeRepositoryImpl(store = store)
}