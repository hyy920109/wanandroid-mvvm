package com.hyy.wanandroid.data.network

import android.util.Log
import com.hyy.wanandroid.BuildConfig
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

const val BASE_URL = "https://www.wanandroid.com/"

class NetworkClient<out T>(val baseUrl: String, clazz: Class<T>) {

    private val httpLogger = object : HttpLoggingInterceptor.Logger {
        override fun log(message: String) {
            Log.d("WanAndroid", message)
        }

    }

    private val serializer: Moshi by lazy { Moshi.Builder().build() }
    private val httpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor(logger = httpLogger).apply {
                level =
                    if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            })
            .build()
    }

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(serializer))
            .client(httpClient)
            .build()
    }

    private val api by lazy { retrofit.create(clazz) }

    fun api(): T = api
}