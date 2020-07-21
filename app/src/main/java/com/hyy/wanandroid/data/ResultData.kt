package com.hyy.wanandroid.data

import com.hyy.wanandroid.data.network.RequestStatus

data class ResultData<out T>(
    val data: T?,
    val status: RequestStatus,
    val errorMsg: String = "Unknown Error",
    val errorCode: Int = RESPONSE_CODE_UNKNOWN
) {
    companion object {

        fun <T> start(): ResultData<T> = ResultData(null, RequestStatus.START)

        fun <T> success(data : T?) : ResultData<T> = ResultData(data, RequestStatus.SUCCESS)

        fun <T> empty() : ResultData<T> = ResultData(null, RequestStatus.EMPTY)

        fun <T> complete() : ResultData<T> = ResultData(null, RequestStatus.COMPLETE)

        fun <T> error(errorMsg: String, errorCode: Int): ResultData<T> = ResultData(null, RequestStatus.ERROR, errorMsg, errorCode)

    }
}