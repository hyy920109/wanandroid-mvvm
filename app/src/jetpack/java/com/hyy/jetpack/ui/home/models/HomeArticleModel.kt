package com.hyy.jetpack.ui.home.models

import com.hyy.data_api_coroutine.model.Article
import com.hyy.jetpack.epoxy.model_helpers.ViewBindingKotlinModel
import com.hyy.wanandroid.R
import com.hyy.wanandroid.databinding.HomeItemArticleBinding

class HomeArticleModel(
    private val article: Article
) : ViewBindingKotlinModel<HomeItemArticleBinding>(R.layout.home_item_article) {

    private var listener: (() -> Unit)? = null

    fun setOnClickListener(listener: () -> Unit) {
        this.listener = listener
    }

    override fun HomeItemArticleBinding.bind() {
        articleName.text = article.title
        chapterName.text = "%s/%s".format(article.superChapterName, article.chapterName)
        author.text = getShareUserOrAuthor(article)
        articleTime.text = article.niceDate

        root.setOnClickListener {
            listener?.invoke()
        }
    }

    private fun getShareUserOrAuthor(item: Article): String = if (item.shareUser.isEmpty()) {
        item.author
    } else {
        "分享自:%s".format(item.shareUser)
    }

}