package com.hyy.rxjava.ext

import com.hyy.data_api_rxjava.exception.ExceptionWrapper

fun Throwable.resolve() = if (this is ExceptionWrapper) {
    this
}else {
    ExceptionWrapper("Unknown Error", ExceptionWrapper.ERROR_CODE_UNKNOWN)
}