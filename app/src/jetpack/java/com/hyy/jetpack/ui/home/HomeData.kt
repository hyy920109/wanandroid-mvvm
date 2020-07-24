package com.hyy.jetpack.ui.home

import com.hyy.data_api_coroutine.model.Banner
import com.hyy.data_api_coroutine.model.HomeArticleList

data class HomeData(val banners: List<Banner>, val homeArticleList: HomeArticleList)