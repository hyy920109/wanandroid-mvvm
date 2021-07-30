package com.hyy.jetpack.ui.home

import com.airbnb.epoxy.EpoxyController
import com.hyy.data_api_coroutine.model.Article
import com.hyy.data_api_coroutine.model.Banner
import com.hyy.jetpack.ui.home.models.HomeArticleModel
import com.hyy.jetpack.ui.home.models.homeBanner

typealias OnItemClickListener = (type: Int, data: Any?) -> Unit
const val ITEM_TYPE_BANNER = 0
const val ITEM_TYPE_ARTICLE = 1

class HomeController : EpoxyController() {

    private val banners = mutableListOf<Banner>()
    private val articles = mutableListOf<Article>()

    private var onItemClickListener: OnItemClickListener? = null

    override fun buildModels() {
        if (banners.isNotEmpty() && articles.isNotEmpty()) {
            homeBanner {
                id("homeBanner")
                banners(this@HomeController.banners)
            }

            articles.forEachIndexed { index, article ->
                HomeArticleModel(article) {
                    onItemClickListener?.invoke(ITEM_TYPE_ARTICLE, it)
                }.apply {
                    id("HomeArticleModel $index")
                    addTo(this@HomeController)
                }
            }

        }

    }

    fun setData(homeData: HomeData) {
        banners.clear()
        articles.clear()

        banners.addAll(homeData.banners)
        articles.addAll(homeData.homeArticleList.articleList)
        requestModelBuild()
    }

    fun addMoreArticles(articles: List<Article>) {
        this.articles.addAll(articles)
        requestModelBuild()
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }
}