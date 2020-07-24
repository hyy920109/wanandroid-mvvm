package com.hyy.data_coroutine.ext

import com.hyy.data_coroutine.RESPONSE_CODE_SUCCESS
import com.hyy.data_api_coroutine.exception.ExceptionWrapper
import com.hyy.data_coroutine.network.model.BaseModel


fun <T> BaseModel<T>.transform(): T {
    return if (errorCode == RESPONSE_CODE_SUCCESS) {
        return data
    } else {
        throw ExceptionWrapper(errorMsg, errorCode)
    }
}
