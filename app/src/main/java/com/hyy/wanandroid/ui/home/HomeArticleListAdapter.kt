package com.hyy.wanandroid.ui.home

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.hyy.wanandroid.R
import com.hyy.wanandroid.data.bean.Article
import com.hyy.wanandroid.data.bean.Banner
import com.youth.banner.indicator.CircleIndicator
import kotlinx.android.synthetic.main.home_item_banner.view.*

const val ITEM_TYPE_BANNER = 0
const val ITEM_TYPE_ARTICLE = 1
class HomeArticleListAdapter : BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder>() {

    init {
        addItemType(ITEM_TYPE_BANNER, R.layout.home_item_banner)
        addItemType(ITEM_TYPE_ARTICLE, R.layout.home_item_article)
    }

    internal class BannerItem(val banners: List<Banner>) : MultiItemEntity {
        override val itemType: Int
            get() = ITEM_TYPE_BANNER

    }

    internal class ArticleItem(val article: Article) : MultiItemEntity {
        override val itemType: Int
            get() = ITEM_TYPE_ARTICLE

    }

    override fun convert(holder: BaseViewHolder, item: MultiItemEntity) {
       when(item.itemType) {
           ITEM_TYPE_BANNER -> {
//               val banners = (item as BannerItem).banners
//               val view = holder.getView<BannerView<Banner, BannerItemAdapter>>(R.id.banner)
//                view.apply {
//                    adapter = BannerItemAdapter(banners)
//                    indicator = CircleIndicator(context)
//                    start()
//                }
           }
           ITEM_TYPE_ARTICLE -> {
               val article = (item as ArticleItem).article
               holder.setText(R.id.article_name, article.title)
                   .setText(R.id.chapter_name, article.chapterName)
                   .setText(R.id.article_time, article.niceDate)
           }
       }
    }


}