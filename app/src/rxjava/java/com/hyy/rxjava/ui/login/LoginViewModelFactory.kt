package com.hyy.rxjava.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hyy.data_api_rxjava.repository.ILoginRepository


/**
 * Created by heCunCun on 2020/8/11
 */
class LoginViewModelFactory(val loginRepository: ILoginRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(loginRepository) as T
        }
        throw ClassCastException("your class is not LoginViewModel")
    }


}