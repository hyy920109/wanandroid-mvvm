package com.hyy.data_rxjava

import com.hyy.data_api_rxjava.model.LoginBean
import com.hyy.data_api_rxjava.repository.ILoginRepository
import com.hyy.data_rxjava.ext.transform
import com.hyy.data_rxjava.mapper.toDomain
import com.hyy.data_rxjava.transform.ExceptionTransform
import io.reactivex.Single
import java.lang.Exception

/**
 * Created by heCunCun on 2020/8/11
 */
class LoginRepository(private val store: Store):ILoginRepository {
    override fun login(username: String, password: String): Single<LoginBean> {
       return store.remoteProvider().loginCall(username,password).map {
           it.transform()
       }.map {
           it.toDomain()
       }.compose(ExceptionTransform.singleErrorTransform())
    }
}