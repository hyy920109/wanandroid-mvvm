package com.hyy.data_api_rxjava.exception


class ExceptionWrapper(val errorMsg: String, val errorCode: Int) : Throwable(errorMsg) {

    companion object {
        const val ERROR_CODE_NOT_LOGIN = -1001
        const val ERROR_CODE_UNKNOWN = -2020
        const val ERROR_CODE_NO_NETWORK = -2021
    }
}