package com.hyy.wanandroid.data.network

import com.hyy.wanandroid.data.RESPONSE_CODE_SUCCESS
import com.hyy.wanandroid.data.ResultData
import com.hyy.wanandroid.data.model.BaseModel
import kotlinx.coroutines.CoroutineScope

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


fun CoroutineScope.startRequest(

) {

}