package com.hyy.rxjava.base

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

abstract class BaseViewModel : ViewModel() {

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

    override fun onCleared() {
        _disposable.clear()
        super.onCleared()
    }
}