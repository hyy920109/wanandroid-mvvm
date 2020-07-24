package com.hyy.rxjava.provider

import com.hyy.rxjava.ui.home.HomeViewModelFactory


object ViewModelFactoryProvider {

    fun getHomeViewModelFactory() : HomeViewModelFactory {
        return HomeViewModelFactory(RepositoryProvider.getHomeRepository())
    }
}