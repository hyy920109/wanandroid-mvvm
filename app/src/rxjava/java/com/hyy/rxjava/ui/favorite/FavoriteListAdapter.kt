package com.hyy.rxjava.ui.favorite

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.hyy.data_api_rxjava.model.Article
import com.hyy.wanandroid.R

/**
 *Create by hyy on 2020/7/31
 */
class FavoriteListAdapter : BaseQuickAdapter<Article, BaseViewHolder>(R.layout.mine_favorite_item_article) {

    override fun convert(holder: BaseViewHolder, item: Article) {

    }
}