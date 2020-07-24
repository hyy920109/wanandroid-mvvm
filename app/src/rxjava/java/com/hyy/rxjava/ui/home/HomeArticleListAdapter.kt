package com.hyy.rxjava.ui.home

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.hyy.data_api_rxjava.model.Article
import com.hyy.wanandroid.R

class HomeArticleListAdapter :
    BaseQuickAdapter<Article, BaseViewHolder>(R.layout.home_item_article), LoadMoreModule {
    override fun convert(holder: BaseViewHolder, item: Article) {
        holder.setText(R.id.article_name, item.title)
            .setText(R.id.chapter_name, "%s/%s".format(item.superChapterName, item.chapterName))
            .setText(R.id.author, getShareUserOrAuthor(item))
            .setText(R.id.article_time, item.niceDate)
    }

    private fun getShareUserOrAuthor(item: Article): String = if (item.shareUser.isEmpty()) {
        item.author
    } else {
        "分享自:%s".format(item.shareUser)
    }

}