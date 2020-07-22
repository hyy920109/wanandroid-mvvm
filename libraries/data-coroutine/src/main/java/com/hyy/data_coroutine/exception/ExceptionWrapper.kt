package com.hyy.data_coroutine.exception


class ExceptionWrapper(val errorMsg: String, val errorCode: Int) : Throwable(errorMsg) {

    companion object {
        const val ERROR_CODE_NOT_LOGIN = -1001
        const val ERROR_CODE_UNKNOWN = -2020
        const val ERROR_CODE_NO_NERWORK = -2021
    }
}