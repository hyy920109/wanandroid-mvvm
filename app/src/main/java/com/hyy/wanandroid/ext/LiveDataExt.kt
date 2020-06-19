package com.hyy.wanandroid.ext

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.hyy.wanandroid.data.ResultData
import com.hyy.wanandroid.data.network.RequestStatus

fun <T> MediatorLiveData<ResultData<T>>.addSource(liveData: LiveData<ResultData<T>>) {
    this.addSource(liveData) {
        if (it.status == RequestStatus.COMPLETE) {
            this.removeSource(liveData)
        }
        this.value = it
    }
}