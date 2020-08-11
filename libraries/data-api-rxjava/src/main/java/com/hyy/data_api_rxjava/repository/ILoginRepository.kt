package com.hyy.data_api_rxjava.repository

import com.hyy.data_api_rxjava.model.LoginBean
import io.reactivex.Single

/**
 * Created by heCunCun on 2020/8/11
 */
interface ILoginRepository {
    fun login(username:String,password:String):Single<LoginBean>

}