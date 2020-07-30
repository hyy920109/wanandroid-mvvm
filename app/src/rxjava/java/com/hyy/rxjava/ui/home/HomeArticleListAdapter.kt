package com.hyy.rxjava.ui.home

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.hyy.data_api_rxjava.model.Article
import com.hyy.wanandroid.R

class HomeArticleListAdapter(private val favoriteIds: MutableSet<Int> = mutableSetOf()) :
    BaseQuickAdapter<Article, BaseViewHolder>(R.layout.home_item_article), LoadMoreModule {

    fun updateFavoriteArticles(mutableSet: MutableSet<Int>) {
        favoriteIds.clear()
        favoriteIds.addAll(mutableSet)
        notifyDataSetChanged()
    }

    override fun convert(holder: BaseViewHolder, item: Article) {
        val favorite = favoriteIds.contains(item.id)
        holder.setText(R.id.article_name, item.title)
            .setText(R.id.chapter_name, "%s/%s".format(item.superChapterName, item.chapterName))
            .setText(R.id.author, getShareUserOrAuthor(item))
            .setText(R.id.article_time, item.niceDate)
            .setImageResource(R.id.btn_favorite, if (favorite) R.drawable.ic_favorite else R.drawable.ic_favorite_outline_smaller)
    }

    private fun getShareUserOrAuthor(item: Article): String = if (item.shareUser.isEmpty()) {
        item.author
    } else {
        "分享自:%s".format(item.shareUser)
    }

}