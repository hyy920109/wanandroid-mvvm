package com.hyy.rxjava.ui.mine

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.hyy.rxjava.ui.history.HistoryActivity
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
        mBinding.itemMineHistory.setOnClickListener {
            Intent(requireContext(), HistoryActivity::class.java).run {
                startActivity(this)
            }
        }

        mBinding.cvLogin.setOnClickListener {
            //去登录
        }
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
