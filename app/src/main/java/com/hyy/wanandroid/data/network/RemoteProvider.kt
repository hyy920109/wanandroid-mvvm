package com.hyy.wanandroid.data.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.hyy.wanandroid.data.RESPONSE_CODE_UNKNOWN
import com.hyy.wanandroid.data.ResultData
import com.hyy.wanandroid.data.WanAndroidApi
import com.hyy.wanandroid.data.bean.HomeArticleList
import com.hyy.wanandroid.data.toDomain
import kotlinx.coroutines.flow.*

//
class RemoteProvider(private val networkClient: NetworkClient<WanAndroidApi>) {

    suspend fun getHomeArticle(page: Int): LiveData<ResultData<HomeArticleList>> {
        return liveData {
            flow {
                emit(ResultData.start())
                val data = networkClient.api().getArticle(page).transform()
                emit(data)
            }.map {
                if (it.status == RequestStatus.SUCCESS) {
                    ResultData(it.data?.toDomain(), it.status)
                } else {
                    it
                }
            }.catch { exception ->
                ResultData.error<HomeArticleList>(
                    exception.localizedMessage ?: "",
                    RESPONSE_CODE_UNKNOWN
                )
            }
        }
    }


}