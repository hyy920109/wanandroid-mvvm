package com.hyy.rxjava.provider

import android.content.Context
import com.hyy.data_api_rxjava.repository.IHomeRepository
import com.hyy.data_api_rxjava.repository.ILocalArticleRepository
import com.hyy.data_api_rxjava.repository.ILoginRepository
import com.hyy.data_rxjava.HomeRepository
import com.hyy.data_rxjava.LocalArticleRepository
import com.hyy.data_rxjava.LoginRepository
import com.hyy.data_rxjava.Store

object RepositoryProvider {

    private lateinit var store: Store

    @JvmStatic
    fun inject(context: Context) {
        store = Store.getInstance(context)
    }

    fun getHomeRepository(): IHomeRepository = HomeRepository(store = store)

    fun getLocalArticleRepo(): ILocalArticleRepository = LocalArticleRepository(store = store)

    fun getLoginRepo(): ILoginRepository = LoginRepository(store = store)
}