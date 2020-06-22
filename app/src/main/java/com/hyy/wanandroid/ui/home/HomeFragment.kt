package com.hyy.wanandroid.ui.home

import android.util.Log
import android.view.LayoutInflater
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

        adapter.loadMoreModule.isEnableLoadMore =true
        adapter.loadMoreModule.setOnLoadMoreListener {
            Log.d(TAG, "setupViews: to load more")
            homeViewModel.loadMore()
        }
    }

    override fun setupListeners() {
    }

    @ExperimentalCoroutinesApi
    override fun setupObservers() {
        homeViewModel.homeData.observe(viewLifecycleOwner, Observer {
            Log.d(TAG, "setupObservers: homeData")
            setupHomeData(it)
        })
        homeViewModel.homeArticleList.observe(viewLifecycleOwner, Observer {
            Log.d(TAG, "setupObservers: homeArticleList")
            setupArticleList(it)
        })
    }

    private fun setupHomeData(homeData: ResultData<HomeData>) {
        homeData.apply {
            when(status) {
                RequestStatus.SUCCESS -> {
                    data?.apply {
                        banners.apply {
                            setupBannerView(this)
                        }
                        adapter.setList(homeArticleList.articleList)
                    }
                    mBinding.statusLayout.showContent()
                    adapter.loadMoreModule.loadMoreComplete()
                }
                RequestStatus.ERROR -> {
                    mBinding.statusLayout.showError()
                    Toast.makeText(requireContext(), errorMsg, Toast.LENGTH_SHORT).show()
                }
                RequestStatus.START -> {
                    mBinding.statusLayout.showLoading()
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
                       adapter.addData(articleList)
                       adapter.loadMoreModule.loadMoreComplete()
                   }
               }
               RequestStatus.EMPTY -> {

               }
               RequestStatus.ERROR -> {
                   Toast.makeText(requireContext(), errorMsg, Toast.LENGTH_SHORT).show()
               }
               RequestStatus.START -> {

               }
           }
       }
    }
}
