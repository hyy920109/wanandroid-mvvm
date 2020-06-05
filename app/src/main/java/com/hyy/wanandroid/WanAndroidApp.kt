package com.hyy.wanandroid

import android.app.Application
import com.hyy.wanandroid.data.RepositoryProvider

class WanAndroidApp : Application() {

    override fun onCreate() {
        super.onCreate()

        RepositoryProvider.inject(context = this)
    }
}