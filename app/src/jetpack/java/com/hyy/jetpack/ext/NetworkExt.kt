package com.hyy.jetpack.ext

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.hyy.data_coroutine.RESPONSE_MSG_UNKNOWN
import com.hyy.data_api_coroutine.exception.ExceptionWrapper
import com.hyy.jetpack.data_base.ResultData
import com.hyy.jetpack.data_base.RequestStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import java.io.IOException

fun <T> CoroutineScope.simpleRequest(api: (suspend () -> T)): LiveData<ResultData<T>> {

    return liveData {
        //请求开始
        emit(ResultData.start())

        //请求
        try {
            val data = api.invoke()
            emit(ResultData.success(data))
        } catch (e: Exception) {
            val error = resolveError(e)
            emit(ResultData.error<T>(error.errorMsg, error.errorCode))
        }
    }
}

inline fun <T> CoroutineScope.request(crossinline block: (suspend () -> ResultData<T>)): Flow<ResultData<T>> {
    return flow {
        emit(ResultData.start())
        val data = block.invoke()
        emit(data)
    }.catch { e ->
        val exception = resolveError(e)
        emit(
            ResultData(
                data = null,
                status = RequestStatus.ERROR,
                errorMsg = exception.errorMsg,
                errorCode = exception.errorCode
            )
        )
    }.onEach {
        Log.d("Network", "data status -->${it.status}")
    }
}

fun CoroutineScope.resolveError(e: Throwable): ExceptionWrapper {
    return if (e is ExceptionWrapper) {
        e
    } else if (e is IOException) {
        ExceptionWrapper(RESPONSE_MSG_UNKNOWN, ExceptionWrapper.ERROR_CODE_NO_NERWORK)
    } else {
        ExceptionWrapper(e.message ?: RESPONSE_MSG_UNKNOWN, ExceptionWrapper.ERROR_CODE_UNKNOWN)
    }
}