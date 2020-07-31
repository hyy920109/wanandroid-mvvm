package com.hyy.rxjava.ui.history

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.hyy.data_api_rxjava.model.Article
import com.hyy.wanandroid.R

/**
 *Create by hyy on 2020/7/31
 */
class HistoryListAdapter :
    BaseQuickAdapter<Article, BaseViewHolder>(R.layout.mine_history_item_article) {

    override fun convert(holder: BaseViewHolder, item: Article) {
        holder.setText(R.id.article_name, item.title)
            .setText(R.id.chapter_name, "%s/%s".format(item.superChapterName, item.chapterName))
            .setText(R.id.author, getShareUserOrAuthor(item))
            .setText(R.id.article_time, item.niceDate)
            .setImageResource(
                R.id.btn_favorite,
                if (item.favorite) R.drawable.ic_favorite else R.drawable.ic_favorite_outline_smaller
            )
    }

    private fun getShareUserOrAuthor(item: Article): String = if (item.shareUser.isEmpty()) {
        item.author
    } else {
        "分享自:%s".format(item.shareUser)
    }
}