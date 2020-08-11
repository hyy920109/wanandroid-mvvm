package com.hyy.rxjava.ui.login

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.hyy.data_api_rxjava.model.LoginBean
import com.hyy.data_rxjava.RESPONSE_CODE_SUCCESS
import com.hyy.rxjava.base.RxBaseFragment
import com.hyy.rxjava.data_base.RequestStatus
import com.hyy.rxjava.data_base.ResultData
import com.hyy.rxjava.provider.ViewModelFactoryProvider
import com.hyy.rxjava.ui.home.HomeFragment
import com.hyy.wanandroid.databinding.FragmentLoginBinding
import io.reactivex.android.schedulers.AndroidSchedulers

/**
 * Created by heCunCun on 2020/8/10
 */
class LoginFragment :RxBaseFragment<FragmentLoginBinding>() {
    private val loginViewModel by viewModels<LoginViewModel>{
        ViewModelFactoryProvider.getLoginViewModelFactory()
    }


    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentLoginBinding = FragmentLoginBinding.inflate(inflater,container,false)


    override fun setupViews() {

    }

    override fun setupListeners() {
        mBinding.btnLogin.setOnClickListener {
            loginViewModel.doLogin(mBinding.etAccount.text.toString(),mBinding.etPwd.text.toString())
        }

    }

    override fun setupObservers() {
        addDisposable{
            loginViewModel.loginData
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext {
                    setupLoginState(it)
                }
                .subscribe()
        }

    }

    private fun setupLoginState(it: ResultData<LoginBean>) {
        it.apply {
            when (it.status) {
                RequestStatus.SUCCESS -> {
                    data?.apply {
                        Toast.makeText(requireContext(),"${it.data?.nickname}登录成功",Toast.LENGTH_SHORT).show()
                        requireActivity().finish()
                    }

                }
                RequestStatus.ERROR -> {
                    Log.d(HomeFragment.TAG, "setupHomeData: error")
                    Toast.makeText(requireContext(), errorMsg, Toast.LENGTH_SHORT).show()
                }
                RequestStatus.START -> {
                }
            }
        }


    }

    companion object{
        fun create() :LoginFragment{
            return LoginFragment().apply {

            }
        }
    }
}