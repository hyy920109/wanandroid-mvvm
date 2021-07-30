package com.hyy.jetpack.ext

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hyy.data_api_coroutine.exception.ExceptionWrapper
import com.hyy.data_coroutine.RESPONSE_MSG_UNKNOWN
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.io.IOException

fun <T> ViewModel.simpleRequest(
    api: suspend () -> T,
    loadingBlock: (() -> Unit)? = null,
    successBlock: (T) -> Unit,
    errorBlock: (e: ExceptionWrapper) -> Unit
) {
    viewModelScope.launch {
        try {
            loadingBlock?.invoke()
            //想多个接口同时异步调用的话 需要用viewModelScope.async{}
            val data = api()
            successBlock(data)
        } catch (e: Exception) {
            println("HYY-WanAndroid --->$e")
            val error = resolveError(e)
            errorBlock(error)
        }
    }
}

fun <T, T2> ViewModel.combineRequest(
    api: suspend () -> T,
    api2: suspend () -> T2,
    loadingBlock: () -> Unit,
    successBlock: (T, T2) -> Unit,
    errorBlock: (e: ExceptionWrapper) -> Unit
) {
    viewModelScope.launch {
        loadingBlock()
        flow {
            val t1 = api()
            emit(t1)
        }
            .combine(flow<T2> {
                val t2 = api2()
                emit(t2)
            }) { t, t2 ->
                println("HYY combine requestLayout")
                successBlock(t, t2)
            }.catch { e ->
                val error = resolveError(e)
                errorBlock(error)
            }
            .collect {

            }
    }
}

//类似与rxJava的zip请求
inline fun <T, T2> ViewModel.zipRequest(
    crossinline api: suspend () -> T,
    crossinline api2: suspend () -> T2,
    crossinline loadingBlock: () -> Unit,
    crossinline successBlock: (T, T2) -> Unit,
    crossinline errorBlock: (e: ExceptionWrapper) -> Unit
) {
    viewModelScope.launch {
        try {
            loadingBlock()
            //想多个接口同时异步调用的话 需要用viewModelScope.async{}
            val dataFuture = viewModelScope.async { api() }
            val data2Future = viewModelScope.async { api2() }
            val data = dataFuture.await()
            val data2 = data2Future.await()
            successBlock(data, data2)
        } catch (e: Exception) {
            println("HYY-WanAndroid --->$e")
            val error = resolveError(e)
            errorBlock(error)
        }
    }
}

fun resolveError(e: Throwable): ExceptionWrapper {
    return if (e is ExceptionWrapper) {
        e
    } else if (e is IOException) {
        ExceptionWrapper(RESPONSE_MSG_UNKNOWN, ExceptionWrapper.ERROR_CODE_NO_NERWORK)
    } else {
        ExceptionWrapper(e.message ?: RESPONSE_MSG_UNKNOWN, ExceptionWrapper.ERROR_CODE_UNKNOWN)
    }
}