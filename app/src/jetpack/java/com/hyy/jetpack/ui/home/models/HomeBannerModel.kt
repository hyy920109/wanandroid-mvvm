package com.hyy.jetpack.ui.home.models

import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.hyy.data_api_coroutine.model.Banner
import com.hyy.jetpack.epoxy.model_helpers.ViewBindingEpoxyModelWithHolder
import com.hyy.jetpack.epoxy.model_helpers.ViewBindingHolder
import com.hyy.jetpack.ui.home.BannerItemAdapter
import com.hyy.wanandroid.R
import com.hyy.wanandroid.databinding.ItemHomeBannerBinding
import com.youth.banner.indicator.CircleIndicator

@EpoxyModelClass(layout = R.layout.item_home_banner)
abstract class HomeBannerModel() : ViewBindingEpoxyModelWithHolder<ItemHomeBannerBinding>() {

    @EpoxyAttribute
    lateinit var banners: List<Banner>

    override fun ItemHomeBannerBinding.bind() {
        banner.apply {
            adapter = BannerItemAdapter(banners)
            indicator = CircleIndicator(context)
            start()
        }
    }

    override fun onViewAttachedToWindow(holder: ViewBindingHolder) {
        super.onViewAttachedToWindow(holder)
        if (holder.viewBinding is ItemHomeBannerBinding) {
            val binding = holder.viewBinding as ItemHomeBannerBinding
            binding.banner.start()
        }
    }

    override fun onViewDetachedFromWindow(holder: ViewBindingHolder) {
        super.onViewDetachedFromWindow(holder)
        if (holder.viewBinding is ItemHomeBannerBinding) {
            val binding = holder.viewBinding as ItemHomeBannerBinding
            binding.banner.stop()
        }
    }
}