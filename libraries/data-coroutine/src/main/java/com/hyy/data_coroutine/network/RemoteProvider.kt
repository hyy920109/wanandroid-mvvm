package com.hyy.data_coroutine.network

import android.util.Log
import com.hyy.data_api_coroutine.model.Banner
import com.hyy.data_api_coroutine.model.HomeArticleList
import com.hyy.data_coroutine.ext.transform
import com.hyy.data_coroutine.mapper.toDomain

//
class RemoteProvider(private val networkClient: NetworkClient<WanAndroidApi>) {

    suspend fun getHomeArticle(page: Int): HomeArticleList {
        val data = networkClient.api().getArticle(page).transform()
        Log.d(TAG, "getHomeArticle: ")
        return data.toDomain()
    }


    suspend fun fetchHomeBanner():List<Banner> {
        Log.d(TAG, "fetchHomeBanner: ")
        val data = networkClient.api().getHomeBanner().transform()
        return data.map { it.toDomain() }
    }

    companion object {
        const val TAG = "RemoteProvider"
    }

}