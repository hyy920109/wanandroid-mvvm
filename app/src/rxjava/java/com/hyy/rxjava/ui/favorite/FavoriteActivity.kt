package com.hyy.rxjava.ui.favorite

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * 我的收藏
 * Create by hyy on 2020/7/31
 */
class FavoriteActivity: AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.beginTransaction().apply {
            replace(android.R.id.content, FavoriteFragment.create())
            commit()
        }
    }


}