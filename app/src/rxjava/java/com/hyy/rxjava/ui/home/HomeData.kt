package com.hyy.rxjava.ui.home

import com.hyy.data_api_rxjava.model.Banner
import com.hyy.data_api_rxjava.model.HomeArticleList


data class HomeData(val banners: List<Banner>, val homeArticleList: HomeArticleList)