package com.hyy.rxjava.ui.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.hyy.rxjava.base.RxBaseFragment
import com.hyy.wanandroid.databinding.FragmentFavoriteBinding

/**
 *Create by hyy on 2020/7/31
 */
class FavoriteFragment : RxBaseFragment<FragmentFavoriteBinding>() {

    private val _adapter by lazy {

    }
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentFavoriteBinding = FragmentFavoriteBinding.inflate(inflater, container, false)

    override fun setupViews() {
        mBinding.rvFavoriteList.apply {
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun setupListeners() {
    }

    override fun setupObservers() {

    }

    companion object {
        const val TAG = "FavoriteFragment"

        fun create(): FavoriteFragment {
            return FavoriteFragment().apply {

            }
        }
    }
}