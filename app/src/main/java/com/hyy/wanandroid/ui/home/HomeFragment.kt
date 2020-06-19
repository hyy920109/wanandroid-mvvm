package com.hyy.wanandroid.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.hyy.wanandroid.base.BaseFragment
import com.hyy.wanandroid.data.ResultData
import com.hyy.wanandroid.data.ViewModelFactoryProvider
import com.hyy.wanandroid.data.bean.Banner
import com.hyy.wanandroid.data.bean.HomeArticleList
import com.hyy.wanandroid.data.network.RequestStatus
import com.hyy.wanandroid.databinding.FragmentHomeBinding
import com.jaeger.library.StatusBarUtil
import com.youth.banner.indicator.CircleIndicator
import kotlinx.coroutines.ExperimentalCoroutinesApi


class HomeFragment : BaseFragment<FragmentHomeBinding>() {

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
//        StatusBarUtil.setTranslucentForImageViewInFragment(requireActivity(), mBinding.banner)
    }

    companion object {
        const val TAG = "HomeFragment"

        fun create() : HomeFragment{
            return HomeFragment()
        }
    }

    override fun setupViews() {
        mBinding.rvHomeList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@HomeFragment.adapter
        }
//        adapter.addLoadMoreModule()
    }

    override fun setupListeners() {
    }

    @ExperimentalCoroutinesApi
    override fun setupObservers() {
        homeViewModel.homeData.observe(viewLifecycleOwner, Observer {
            setupHomeData(it)
        })
        homeViewModel.homeArticleList.observe(viewLifecycleOwner, Observer {
            setupArticleList(it)
        })
    }

    private fun setupHomeData(homeData: ResultData<HomeData>) {
        homeData?.apply {
            when(status) {
                RequestStatus.SUCCESS -> {
                    data?.apply {
                        banners.apply {
                            setupBannerView(this)
                        }
                        homeArticleList.articleList.map {
                            HomeArticleListAdapter.ArticleItem(it)
                        }.apply {
                            adapter.addData(this)
                        }
                    }
                    Toast.makeText(requireContext(), "网络请求成功了", Toast.LENGTH_SHORT).show()
                }
                RequestStatus.ERROR -> {
                    Toast.makeText(requireContext(), errorMsg, Toast.LENGTH_SHORT).show()
                }
                RequestStatus.START -> {
                    Toast.makeText(requireContext(), "正在请求数据", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setupBannerView(banners: List<Banner>) {
        mBinding.banner.apply {
            addBannerLifecycleObserver(this@HomeFragment)
            adapter = BannerItemAdapter(banners)
            indicator = CircleIndicator(context)
            start()
        }
    }

    private fun setupArticleList(resultData: ResultData<HomeArticleList>?) {
       resultData?.apply {
           when(status) {
               RequestStatus.SUCCESS -> {
                   data?.apply {
                       this.articleList.map {
                           HomeArticleListAdapter.ArticleItem(it)
                       }.apply {
                           adapter.addData(this)
                       }
                   }
               }
               RequestStatus.EMPTY -> {

               }
               RequestStatus.ERROR -> {
                   Toast.makeText(requireContext(), errorMsg, Toast.LENGTH_SHORT).show()
               }
               RequestStatus.START -> {
                   Toast.makeText(requireContext(), "正在请求数据", Toast.LENGTH_SHORT).show()
               }
           }
       }
    }
}
