package com.hyy.jetpack.ext

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.hyy.jetpack.data_base.ResultData
import com.hyy.jetpack.data_base.RequestStatus


fun <T> MediatorLiveData<ResultData<T>>.addSource(liveData: LiveData<ResultData<T>>) {
    this.addSource(liveData) {
        if (it.status == RequestStatus.COMPLETE) {
            this.removeSource(liveData)
        }
        this.value = it
    }
}