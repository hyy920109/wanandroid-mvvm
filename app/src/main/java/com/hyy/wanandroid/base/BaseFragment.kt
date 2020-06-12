package com.hyy.wanandroid.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    private var _binding: VB? = null
    protected val mBinding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        Log.d("BaseFragment", "onCreateView: ")
        _binding = getViewBinding(inflater, container)
        return mBinding.root
    }

    abstract fun getViewBinding(inflater: LayoutInflater,
                                container: ViewGroup?): VB

    @CallSuper
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}
