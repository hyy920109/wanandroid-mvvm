package com.hyy.jetpack.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hyy.data_api_coroutine.model.Banner
import com.hyy.wanandroid.databinding.HomeBannerSingleItemBinding
import com.youth.banner.adapter.BannerAdapter

class BannerItemAdapter(val banners: List<Banner>) : BannerAdapter<Banner, BannerViewHolder>(banners) {

    override fun onCreateHolder(parent: ViewGroup?, viewType: Int): BannerViewHolder {
        val binding =
            HomeBannerSingleItemBinding.inflate(LayoutInflater.from(parent?.context), parent, false)

        return BannerViewHolder(binding)
    }

    override fun onBindView(holder: BannerViewHolder?, data: Banner?, position: Int, size: Int) {
        holder?.bind(data)
    }
}

class BannerViewHolder(private val binding: HomeBannerSingleItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(data: Banner?) {
        data?.apply {
            Glide.with(binding.image).load(imagePath).into(binding.image)
        }
    }
}