package com.hyy.rxjava.provider

import com.hyy.rxjava.ui.history.HistoryViewModelFactory
import com.hyy.rxjava.ui.home.HomeViewModelFactory


object ViewModelFactoryProvider {

    fun getHomeViewModelFactory() : HomeViewModelFactory {
        return HomeViewModelFactory(RepositoryProvider.getHomeRepository(), RepositoryProvider.getLocalArticleRepo())
    }

    fun getHistoryViewModelFactory() : HistoryViewModelFactory {
        return HistoryViewModelFactory(RepositoryProvider.getLocalArticleRepo())
    }
}