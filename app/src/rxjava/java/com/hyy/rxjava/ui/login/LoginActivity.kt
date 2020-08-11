package com.hyy.rxjava.ui.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * Created by heCunCun on 2020/8/10
 */
class LoginActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.beginTransaction().apply {
            replace(android.R.id.content,LoginFragment.create())
            commit()
        }
    }
}