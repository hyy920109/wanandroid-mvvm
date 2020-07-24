package com.hyy.data_rxjava.ext

import com.hyy.data_api_rxjava.exception.ExceptionWrapper
import com.hyy.data_rxjava.RESPONSE_CODE_SUCCESS
import com.hyy.data_rxjava.network.model.BaseModel


fun <T> BaseModel<T>.transform(): T {
    return if (errorCode == RESPONSE_CODE_SUCCESS) {
        return data
    } else {
        throw ExceptionWrapper(errorMsg, errorCode)
    }
}
