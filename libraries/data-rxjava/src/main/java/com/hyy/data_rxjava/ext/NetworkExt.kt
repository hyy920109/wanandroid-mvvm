package com.hyy.data_rxjava.ext

import com.hyy.data_api_rxjava.exception.ExceptionWrapper
import com.hyy.data_rxjava.RESPONSE_CODE_SUCCESS
import com.hyy.data_rxjava.network.model.BaseModel


fun <T> BaseModel<T>.transform(): T {
    return if (errorCode == RESPONSE_CODE_SUCCESS) {
        return data
    } else {
        //todo 此处做成失败的回调最好   针对不同的errorCode 提示不同的错误信息
        throw ExceptionWrapper(errorMsg, errorCode)
    }
}
