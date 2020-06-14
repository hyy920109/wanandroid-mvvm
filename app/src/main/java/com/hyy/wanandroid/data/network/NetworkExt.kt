package com.hyy.wanandroid.data.network

import android.util.Log
import com.hyy.wanandroid.data.RESPONSE_CODE_SUCCESS
import com.hyy.wanandroid.data.RESPONSE_CODE_UNKNOWN
import com.hyy.wanandroid.data.RESPONSE_MSG_UNKNOWN
import com.hyy.wanandroid.data.ResultData
import com.hyy.wanandroid.data.model.BaseModel
import com.hyy.wanandroid.ui.home.HomeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach

fun <T> BaseModel<T>.transform() : ResultData<T> {
    return if (errorCode == RESPONSE_CODE_SUCCESS) {
        if (data == null) {
            ResultData.empty()
        }else {
            ResultData.success(data)
        }
    }else {
        ResultData.error(errorMsg, errorCode)
    }
}

inline fun <T> CoroutineScope.request(crossinline block : (suspend () -> ResultData<T>)) : Flow<ResultData<T>>{
    return flow {
        emit(ResultData.start())
        val data = block.invoke()
        emit(data)
    }.catch {e->
        emit(ResultData(
            data = null,
            status = RequestStatus.ERROR,
            errorMsg = e.message ?: RESPONSE_MSG_UNKNOWN,
            errorCode = RESPONSE_CODE_UNKNOWN
        ))
    }.onEach {
        Log.d("Network", "data status -->${it.status}")
    }
}
