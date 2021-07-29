package com.hyy.jetpack.ui.home

import com.airbnb.epoxy.EpoxyController
import com.hyy.data_api_coroutine.model.Article
import com.hyy.data_api_coroutine.model.Banner
import com.hyy.jetpack.ui.home.models.HomeArticleModel
import com.hyy.jetpack.ui.home.models.homeBanner

class HomeController : EpoxyController() {

    private val banners = mutableListOf<Banner>()
    private val articles = mutableListOf<Article>()

    override fun buildModels() {
        if (banners.isNotEmpty() && articles.isNotEmpty()) {
            homeBanner {
                id("homeBanner")
                banners(this@HomeController.banners)
            }

            articles.forEachIndexed { index, article ->
                HomeArticleModel(article)
                    .id("HomeArticleModel $index")
                    .addTo(this)
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
}