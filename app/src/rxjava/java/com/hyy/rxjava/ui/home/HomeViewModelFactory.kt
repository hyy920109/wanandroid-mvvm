package com.hyy.rxjava.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hyy.data_api_rxjava.repository.IHomeRepository
import com.hyy.data_api_rxjava.repository.ILocalArticleRepository
import kotlin.ClassCastException

class HomeViewModelFactory(val homeRepository: IHomeRepository, val localArticleRepository: ILocalArticleRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(homeRepository, localArticleRepository) as T
        }
        throw ClassCastException("your class is not HomeViewModel")
    }
}