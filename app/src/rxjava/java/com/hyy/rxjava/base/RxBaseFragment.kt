package com.hyy.rxjava.base

import androidx.viewbinding.ViewBinding
import com.hyy.wanandroid.base.BaseFragment
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

abstract class RxBaseFragment<VB: ViewBinding> : BaseFragment<VB>() {

    private val _disposable: CompositeDisposable by lazy {
        CompositeDisposable()
    }

    protected fun addDisposable(disposable: Disposable) {
        _disposable.add(disposable)
    }

    protected fun removeDisposable(disposable: Disposable) {
        _disposable.remove(disposable)
    }

    protected fun addDisposable(block: () -> Disposable) {
        _disposable.add(block.invoke())
    }
}