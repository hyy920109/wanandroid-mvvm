package com.hyy.data_rxjava

import com.hyy.data_api_rxjava.model.Article
import com.hyy.data_api_rxjava.model.Banner
import com.hyy.data_api_rxjava.model.HomeArticleList
import com.hyy.data_api_rxjava.repository.HomeRepository
import com.hyy.data_rxjava.ext.transform
import com.hyy.data_rxjava.mapper.toDomain
import com.hyy.data_rxjava.mapper.toEntity
import com.hyy.data_rxjava.transform.ExceptionTransform
import io.reactivex.Flowable
import io.reactivex.Single


class HomeRepositoryImpl(private val store: Store) : HomeRepository {

    override fun requestHomeArticles(page: Int): Single<HomeArticleList> {
        return store.remoteProvider().getHomeArticle(page)
            .map {
                it.transform().toDomain()
            }.compose(ExceptionTransform.singleErrorTransform())
    }

    override fun fetchHomeBanner(): Single<List<Banner>> {
        return store.remoteProvider().fetchHomeBanner()
            .map { it.transform() }
            .map { it.map { bannerModel ->  bannerModel.toDomain() } }
            .compose(ExceptionTransform.singleErrorTransform())
    }

    override fun addFavorite(article: Article) {
        val articleEntity = article.toEntity()
        articleEntity.favorite = true
        store.localProvider().addFavoriteArticle(articleEntity)
    }

    override fun getFavoriteArticles(): Flowable<List<Article>> {
        return store.localProvider().getAllFavoriteArticles()
            .map {
                it.map { articleEntity ->  articleEntity.toDomain() }
            }
    }
}