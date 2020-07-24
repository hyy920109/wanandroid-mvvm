package com.hyy.jetpack

import android.app.Application
import com.hyy.jetpack.provider.RepositoryProvider

class WanAndroidApp : Application() {

    override fun onCreate() {
        super.onCreate()

        RepositoryProvider.inject(context = this)
    }
}