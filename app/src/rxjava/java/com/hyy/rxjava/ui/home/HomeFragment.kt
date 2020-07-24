package com.hyy.rxjava.ui.home

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.hyy.data_api_rxjava.model.Article
import com.hyy.data_api_rxjava.model.Banner
import com.hyy.data_api_rxjava.model.HomeArticleList
import com.hyy.rxjava.base.RxBaseFragment
import com.hyy.rxjava.data_base.RequestStatus
import com.hyy.rxjava.data_base.ResultData
import com.hyy.rxjava.provider.ViewModelFactoryProvider
import com.hyy.rxjava.ui.web.ArticleWebActivity
import com.hyy.wanandroid.databinding.FragmentHomeBinding
import com.youth.banner.indicator.CircleIndicator
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers


class HomeFragment : RxBaseFragment<FragmentHomeBinding>() {

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
            homeViewModel.loadMore()
        }
    }

    override fun setupListeners() {
        adapter.setOnItemClickListener { adapter, view, position ->
            val item = adapter.getItem(position) as Article
            ArticleWebActivity.start(requireContext(), item.title, item.link)
        }
    }

    override fun setupObservers() {
        addDisposable {
            homeViewModel.homeData
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext { setupHomeData(it) }
                .subscribe()
        }

        addDisposable {
            homeViewModel.moreArticles
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(::setupMoreArticles)
                .subscribe()
        }
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
        mBinding.banner.apply {
            addBannerLifecycleObserver(this@HomeFragment)
            adapter = BannerItemAdapter(banners)
            indicator = CircleIndicator(context)
            start()
        }
    }

    private fun setupMoreArticles(resultData: ResultData<HomeArticleList>) {
       resultData.apply {
           when(status) {
               RequestStatus.SUCCESS -> {
                   data?.apply {
                       adapter.addData(articleList)
                       adapter.loadMoreModule.loadMoreComplete()
                   }
               }
               RequestStatus.EMPTY -> {
                        adapter.loadMoreModule.loadMoreEnd(true)
               }
               RequestStatus.ERROR -> {
                   adapter.loadMoreModule.loadMoreFail()
                   Toast.makeText(requireContext(), errorMsg, Toast.LENGTH_SHORT).show()
               }
               RequestStatus.START -> {

               }
           }
       }
    }
}
