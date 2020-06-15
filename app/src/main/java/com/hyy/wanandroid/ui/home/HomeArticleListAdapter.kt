package com.hyy.wanandroid.ui.home

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.hyy.wanandroid.R
import com.hyy.wanandroid.data.bean.Article

class HomeArticleListAdapter : BaseQuickAdapter<Article, BaseViewHolder>(R.layout.home_item_article) {
    override fun convert(holder: BaseViewHolder, item: Article) {
        holder.setText(R.id.article_name, item.chapterName)
    }
}