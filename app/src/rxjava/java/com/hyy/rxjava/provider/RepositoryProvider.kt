package com.hyy.rxjava.provider

import android.content.Context
import com.hyy.data_api_rxjava.repository.HomeRepository
import com.hyy.data_rxjava.HomeRepositoryImpl
import com.hyy.data_rxjava.Store

object RepositoryProvider {

    private lateinit var store: Store

    @JvmStatic
    fun inject(context: Context) {
        store = Store.getInstance(context)
    }

    @JvmStatic
    fun getHomeRepository() : HomeRepository = HomeRepositoryImpl(store = store)
}