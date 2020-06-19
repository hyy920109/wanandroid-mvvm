package com.hyy.wanandroid.data.network

import android.util.Log
import com.hyy.wanandroid.data.ResultData
import com.hyy.wanandroid.data.WanAndroidApi
import com.hyy.wanandroid.data.bean.Banner
import com.hyy.wanandroid.data.bean.HomeArticleList
import com.hyy.wanandroid.data.toDomain

//
class RemoteProvider(private val networkClient: NetworkClient<WanAndroidApi>) {

    suspend fun getHomeArticle(page: Int): ResultData<HomeArticleList> {
        val data = networkClient.api().getArticle(page).transform()
        Log.d(TAG, "getHomeArticle: ")
        return ResultData(data = data.data?.toDomain(), status = data.status)
    }


    suspend fun fetchHomeBanner(): ResultData<List<Banner>> {
        val data = networkClient.api().getHomeBanner().transform()
        Log.d(TAG, "fetchHomeBanner: ")
        return ResultData(data = data.data?.map { it.toDomain() }, status = data.status)
    }

    companion object {
        const val TAG = "RemoteProvider"
    }

}