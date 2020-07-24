package com.hyy.rxjava.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hyy.data_api_rxjava.repository.HomeRepository
import kotlin.ClassCastException

class HomeViewModelFactory(val homeRepository: HomeRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(homeRepository) as T
        }
        throw ClassCastException("your class is not HomeViewModel")
    }
}