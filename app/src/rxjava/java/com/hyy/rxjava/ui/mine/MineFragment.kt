package com.hyy.rxjava.ui.mine

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.hyy.wanandroid.base.BaseFragment
import com.hyy.wanandroid.databinding.FragmentMineBinding

class MineFragment : BaseFragment<FragmentMineBinding>() {

    private val viewModel: MineViewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(MineViewModel::class.java)
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMineBinding  = FragmentMineBinding.inflate(inflater, container, false)

    override fun setupViews() {
        (requireActivity() as AppCompatActivity).setSupportActionBar(mBinding.toolbar)
    }

    override fun setupListeners() {
    }

    override fun setupObservers() {
    }

    companion object {

        const val TAG = "MineFragment"

        fun create(): MineFragment {
            return MineFragment()
        }
    }


}
