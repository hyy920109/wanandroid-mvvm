package com.hyy.rxjava

import android.app.Application
import com.hyy.rxjava.provider.RepositoryProvider
import com.hyy.wanandroid.BuildConfig
import io.reactivex.rxjava3.plugins.RxJavaPlugins

class WanAndroidApp : Application() {

    override fun onCreate() {
        super.onCreate()
        RxJavaPlugins.setErrorHandler {
            if (BuildConfig.DEBUG) {
                it.printStackTrace()
            }
        }
        RepositoryProvider.inject(context = this)
    }
}