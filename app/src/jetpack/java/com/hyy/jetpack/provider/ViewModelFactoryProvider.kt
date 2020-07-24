package com.hyy.jetpack.provider

import com.hyy.jetpack.ui.home.HomeViewModelFactory

object ViewModelFactoryProvider {

    fun getHomeViewModelFactory() : HomeViewModelFactory{
        return HomeViewModelFactory(RepositoryProvider.getHomeRepository())
    }
}