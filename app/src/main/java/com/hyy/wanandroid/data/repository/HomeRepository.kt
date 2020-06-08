package com.hyy.wanandroid.data.repository

import androidx.lifecycle.LiveData
import com.hyy.wanandroid.data.ResultData
import com.hyy.wanandroid.data.bean.Article
import com.hyy.wanandroid.data.bean.HomeArticleList

interface HomeRepository {

    suspend fun requestHomeArticles(page: Int) : ResultData<HomeArticleList>
}