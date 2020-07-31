package com.hyy.rxjava.ui.history

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.hyy.data_api_rxjava.model.Article
import com.hyy.rxjava.base.RxBaseFragment
import com.hyy.rxjava.data_base.RequestStatus
import com.hyy.rxjava.data_base.ResultData
import com.hyy.rxjava.provider.ViewModelFactoryProvider
import com.hyy.wanandroid.databinding.FragmentFavoriteBinding
import com.hyy.wanandroid.databinding.FragmentHistoryBinding
import io.reactivex.android.schedulers.AndroidSchedulers

/**
 *Create by hyy on 2020/7/31
 */
class HistoryFragment : RxBaseFragment<FragmentHistoryBinding>() {

    private val viewModel by viewModels<HistoryViewModel> {
        ViewModelFactoryProvider.getHistoryViewModelFactory()
    }

    private val _adapter by lazy {
        HistoryListAdapter()
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHistoryBinding = FragmentHistoryBinding.inflate(inflater, container, false)

    override fun setupViews() {
        mBinding.rvFavoriteList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = _adapter
        }
    }

    override fun setupListeners() {
        mBinding.toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    override fun setupObservers() {
        addDisposable {
            viewModel.historyArticles
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext {
                    setupArticleList(it)
                }
                .subscribe()
        }
    }

    private fun setupArticleList(resultData: ResultData<List<Article>>?) {
        resultData?.run {
            when (status) {
                RequestStatus.START -> {
                    mBinding.statusLayout.showLoading()
                }
                RequestStatus.SUCCESS -> {
                    _adapter.setList(data)
                    mBinding.statusLayout.showContent()
                }
                RequestStatus.ERROR -> {
                    Toast.makeText(requireContext(), errorMsg, Toast.LENGTH_SHORT).show()
                    mBinding.statusLayout.showError(errorMsg)
                }
                RequestStatus.EMPTY -> {
                    mBinding.statusLayout.showEmpty()
                }
            }
        }
    }

    companion object {
        const val TAG = "HistoryFragment"

        fun create(): HistoryFragment {
            return HistoryFragment().apply {

            }
        }
    }
}