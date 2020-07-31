package com.hyy.rxjava.ui.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hyy.data_api_rxjava.repository.ILocalArticleRepository

/**
 *Create by hyy on 2020/7/31
 */
class HistoryViewModelFactory(private val localArticleRepo: ILocalArticleRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HistoryViewModel::class.java)) {
            return HistoryViewModel(localArticleRepo) as T
        }
        throw ClassCastException("your class is not HomeViewModel")
    }

}