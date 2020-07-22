package com.hyy.jetpack.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hyy.wanandroid.data.RepositoryProvider
import com.hyy.wanandroid.data.repository.HomeRepository
import kotlin.ClassCastException

class HomeViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(RepositoryProvider.getHomeRepository()) as T
        }
        throw ClassCastException("your class is not HomeViewModel")
    }
}