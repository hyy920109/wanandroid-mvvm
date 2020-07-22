package com.hyy.jetpack.ui.web

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

const val TITLE = "title"
const val URL = "url"
class ArticleWebActivity : AppCompatActivity() {

    private lateinit var title: String
    private lateinit var url: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        resolveIntent()
        supportFragmentManager.beginTransaction().apply {
            replace(android.R.id.content, ArticleWebFragment.create(title, url))
            commitNow()
        }
    }

    private fun resolveIntent() {
        title = intent.getStringExtra(TITLE) ?: ""
        url = intent.getStringExtra(URL) ?: ""
    }

    companion object {

        fun start(context: Context, title: String, url: String) {
            val intent = Intent(context, ArticleWebActivity::class.java).apply {
                putExtra(TITLE, title)
                putExtra(URL, url)
            }
            context.startActivity(intent)
        }
    }
}