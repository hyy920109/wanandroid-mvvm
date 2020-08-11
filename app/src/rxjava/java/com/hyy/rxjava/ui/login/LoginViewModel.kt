package com.hyy.rxjava.ui.login

import android.util.Log
import com.hyy.data_api_rxjava.model.LoginBean
import com.hyy.data_api_rxjava.repository.ILoginRepository
import com.hyy.rxjava.base.BaseViewModel
import com.hyy.rxjava.data_base.ResultData
import com.hyy.rxjava.ui.home.HomeFragment.Companion.TAG
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.SingleOnSubscribe
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import java.util.logging.Logger

/**
 * Created by heCunCun on 2020/8/11
 */
class LoginViewModel(private var loginRepository: ILoginRepository) : BaseViewModel() {
    private val _loginData = PublishSubject.create<ResultData<LoginBean>>()
    val loginData: Observable<ResultData<LoginBean>>
        get() = _loginData.hide()

     fun doLogin(userName: String, pwd: String) {
        _loginData.onNext(ResultData.start())
        //需要去请求网络
        addDisposable {
            loginRepository.login(userName,pwd).doOnSuccess {
                 Log.e("LoginViewModel",it.nickname)
                _loginData.onNext(ResultData.success(it))
            }.doOnError {
                Log.e("LoginViewModel",it.printStackTrace().toString())
            }.subscribe()
        }

    }
}