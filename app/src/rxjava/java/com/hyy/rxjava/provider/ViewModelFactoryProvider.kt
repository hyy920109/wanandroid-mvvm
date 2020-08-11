package com.hyy.rxjava.provider

import com.hyy.rxjava.ui.history.HistoryViewModelFactory
import com.hyy.rxjava.ui.home.HomeViewModelFactory
import com.hyy.rxjava.ui.login.LoginViewModelFactory


object ViewModelFactoryProvider {

    fun getHomeViewModelFactory() : HomeViewModelFactory {
        return HomeViewModelFactory(RepositoryProvider.getHomeRepository(), RepositoryProvider.getLocalArticleRepo())
    }

    fun getHistoryViewModelFactory() : HistoryViewModelFactory {
        return HistoryViewModelFactory(RepositoryProvider.getLocalArticleRepo())
    }

    fun getLoginViewModelFactory():LoginViewModelFactory{
        return  LoginViewModelFactory(RepositoryProvider.getLoginRepo())
    }
}