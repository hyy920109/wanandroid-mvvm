package com.hyy.data_rxjava.network

import com.hyy.data_rxjava.network.model.BannerModel
import com.hyy.data_rxjava.network.model.BaseModel
import com.hyy.data_rxjava.network.model.HomeArticleListModel
import com.hyy.data_rxjava.network.model.LoginModel
import io.reactivex.Single

//
class RemoteProvider(private val networkClient: NetworkClient<WanAndroidApi>) {

    fun getHomeArticle(page: Int): Single<BaseModel<HomeArticleListModel>> {
        return networkClient.api().getArticle(page)
    }


    fun fetchHomeBanner(): Single<BaseModel<List<BannerModel>>> {
        return networkClient.api().getHomeBanner()
    }

    fun loginCall(username:String,password:String):Single<BaseModel<LoginModel>>{
        return networkClient.api().loginCall(username, password)
    }

    companion object {
        const val TAG = "RemoteProvider"
    }

}