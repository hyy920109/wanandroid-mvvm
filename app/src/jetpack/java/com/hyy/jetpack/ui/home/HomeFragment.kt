package com.hyy.jetpack.ui.home

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.hyy.data_api_coroutine.model.Article
import com.hyy.data_api_coroutine.model.Banner
import com.hyy.data_api_coroutine.model.HomeArticleList
import com.hyy.jetpack.provider.ViewModelFactoryProvider
import com.hyy.jetpack.ui.web.ArticleWebActivity
import com.hyy.wanandroid.base.BaseFragment
import com.hyy.jetpack.data_base.ResultData
import com.hyy.jetpack.data_base.RequestStatus
import com.hyy.wanandroid.databinding.FragmentHomeBinding
import com.hyy.wanandroid.databinding.FragmentHomeJetpackBinding
import com.youth.banner.indicator.CircleIndicator
import kotlinx.coroutines.ExperimentalCoroutinesApi


class HomeFragment : BaseFragment<FragmentHomeJetpackBinding>() {

    private val homeViewModel by viewModels<HomeViewModel> {
        ViewModelFactoryProvider.getHomeViewModelFactory()
    }

    private val homeController by lazy {
        HomeController().apply {
            setOnItemClickListener { type, data ->
                when(type) {
                    ITEM_TYPE_ARTICLE -> {
                        if (data is Article) {
                            ArticleWebActivity.start(requireContext(), data.title, data.link)
                        }
                    }
                }
            }
        }
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeJetpackBinding = FragmentHomeJetpackBinding.inflate(inflater, container, false)

    companion object {
        const val TAG = "HomeFragment"

        fun create() : HomeFragment{
            return HomeFragment()
        }
    }

    override fun setupViews() {
        mBinding.rvHomeList.apply {
            adapter = homeController.adapter
            itemAnimator = null
        }

        mBinding.srlRefresh.setEnableRefresh(false)
        mBinding.srlRefresh.setOnLoadMoreListener {
            homeViewModel.loadMore()
        }
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
        homeData.apply {
            when(status) {
                RequestStatus.SUCCESS -> {
                    data?.apply {
                        homeController.setData(this)
                    }
                    mBinding.statusLayout.showContent()
                }
                RequestStatus.ERROR -> {
                    Log.d(TAG, "setupHomeData: error")
                    mBinding.statusLayout.showError()
                    Toast.makeText(requireContext(), errorMsg, Toast.LENGTH_SHORT).show()
                }
                RequestStatus.START -> {
                    mBinding.statusLayout.showLoading()
                }
            }
        }
    }

    private fun setupArticleList(resultData: ResultData<HomeArticleList>?) {
       resultData?.apply {
           when(status) {
               RequestStatus.SUCCESS -> {
                   data?.apply {
                       mBinding.srlRefresh.finishLoadMore(0,true, false)
                       homeController.addMoreArticles(this.articleList)
                   }
               }
               RequestStatus.EMPTY -> {
                   mBinding.srlRefresh.finishLoadMoreWithNoMoreData()
               }
               RequestStatus.ERROR -> {
                   mBinding.srlRefresh.finishLoadMore(false)
                   Toast.makeText(requireContext(), errorMsg, Toast.LENGTH_SHORT).show()
               }
               RequestStatus.START -> {

               }
           }
       }
    }
}
