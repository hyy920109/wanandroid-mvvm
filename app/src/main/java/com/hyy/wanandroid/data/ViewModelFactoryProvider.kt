package com.hyy.wanandroid.data

import com.hyy.wanandroid.ui.home.HomeViewModel
import com.hyy.wanandroid.ui.home.HomeViewModelFactory

object ViewModelFactoryProvider {

    fun getHomeViewModelFactory() : HomeViewModelFactory<HomeViewModel> {
        return HomeViewModelFactory(RepositoryProvider.getHomeRepository())
    }
}