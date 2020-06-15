package com.hyy.wanandroid.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.hyy.wanandroid.R
import com.hyy.wanandroid.base.BaseFragment
import com.hyy.wanandroid.data.ViewModelFactoryProvider
import com.hyy.wanandroid.data.bean.Article
import com.hyy.wanandroid.data.network.RequestStatus
import com.hyy.wanandroid.databinding.FragmentHomeBinding

class HomeFragment() : BaseFragment<FragmentHomeBinding>() {

    private val homeViewModel by viewModels<HomeViewModel> {
        ViewModelFactoryProvider.getHomeViewModelFactory()
    }

    private val adapter: HomeArticleListAdapter by lazy {
        HomeArticleListAdapter()
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.rvHomeList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@HomeFragment.adapter
        }
        homeViewModel.homeArticleList.observe(viewLifecycleOwner, Observer {
            when(it.status) {
                RequestStatus.SUCCESS -> {
                    it.data?.apply {
                        articleList.apply {
//                            Log.d(TAG, "onViewCreated: articles size--> ${this?.size}" )
                                this?.apply {
                                    adapter.setNewInstance(toMutableList())
                            }

                        }
                    }
                    Toast.makeText(requireContext(), "网络请求成功了", Toast.LENGTH_SHORT).show()
                }
                RequestStatus.ERROR -> {
                    Toast.makeText(requireContext(), "网络请求失败了", Toast.LENGTH_SHORT).show()
                }
                RequestStatus.START -> {
                    Toast.makeText(requireContext(), "正在请求数据", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    companion object {
        const val TAG = "HomeFragment"

        fun create() : HomeFragment{
            return HomeFragment()
        }
    }
}
