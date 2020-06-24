package com.hyy.wanandroid.ui.mine

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.hyy.wanandroid.R
import com.hyy.wanandroid.base.BaseFragment
import com.hyy.wanandroid.databinding.FragmentMineBinding

class MineFragment : BaseFragment<FragmentMineBinding>() {

    private val dashboardViewModel: MineViewModel by lazy {
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
