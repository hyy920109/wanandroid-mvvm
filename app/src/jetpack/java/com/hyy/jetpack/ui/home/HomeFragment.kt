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

    private val adapter: HomeArticleListAdapter by lazy {
        HomeArticleListAdapter()
    }

    private val homeController by lazy {
        HomeController()
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
//            layoutManager = LinearLayoutManager(requireContext())
            adapter = homeController.adapter
            itemAnimator = null
        }

//        adapter.loadMoreModule.isEnableLoadMore =true
        mBinding.srlRefresh.setOnLoadMoreListener {
            homeViewModel.loadMore()
        }
//        adapter.loadMoreModule.setOnLoadMoreListener {
//        }

//        lifecycleScope.launchWhenCreated {  }
    }

    override fun setupListeners() {
        adapter.setOnItemClickListener { adapter, view, position ->
            val item = adapter.getItem(position) as Article
            ArticleWebActivity.start(requireContext(), item.title, item.link)
        }
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
//                        banners.apply {
//                            setupBannerView(this)
//                        }
//                        adapter.setList(homeArticleList.articleList)
                        homeController.setData(this)
                    }
                    mBinding.statusLayout.showContent()
                    adapter.loadMoreModule.loadMoreComplete()
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

    private fun setupBannerView(banners: List<Banner>) {
//        mBinding.banner.apply {
//            addBannerLifecycleObserver(this@HomeFragment)
//            adapter = BannerItemAdapter(banners)
//            indicator = CircleIndicator(context)
//            start()
//        }
    }

    private fun setupArticleList(resultData: ResultData<HomeArticleList>?) {
       resultData?.apply {
           when(status) {
               RequestStatus.SUCCESS -> {
                   data?.apply {
                       mBinding.srlRefresh.finishLoadMore(0,true, false)
//                       adapter.addData(articleList)
//                       adapter.loadMoreModule.loadMoreComplete()
                       homeController.addMoreArticles(this.articleList)
                   }
               }
               RequestStatus.EMPTY -> {
                   mBinding.srlRefresh.finishLoadMoreWithNoMoreData()
                   adapter.loadMoreModule.loadMoreEnd(true)
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
