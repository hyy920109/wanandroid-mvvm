package com.hyy.wanandroid.data.network

import com.hyy.wanandroid.data.ResultData
import com.hyy.wanandroid.data.WanAndroidApi
import com.hyy.wanandroid.data.bean.HomeArticleList
import com.hyy.wanandroid.data.toDomain

//
class RemoteProvider(private val networkClient: NetworkClient<WanAndroidApi>) {

    suspend fun getHomeArticle(page: Int): ResultData<HomeArticleList> {
        val data = networkClient.api().getArticle(page).transform()
        return ResultData(data = data.data?.toDomain(), status = data.status)
    }

    companion object {
        const val TAG = "RemoteProvider"
    }

}