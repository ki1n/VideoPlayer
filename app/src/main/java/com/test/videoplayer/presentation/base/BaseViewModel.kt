package com.test.videoplayer.presentation.base

import androidx.annotation.CallSuper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : ViewModel() {

    // Так как ошибки не приходят, сделаем стандартную "Ошибка сервера"
    private val _errorLiveEvent = SingleLiveEvent<Unit>()
    val errorLiveData: LiveData<Unit> = _errorLiveEvent

    private val _unknownErrorLiveEvent = SingleLiveEvent<Unit>()
    val unknownErrorLiveEvent: LiveData<Unit> = _unknownErrorLiveEvent

    private val _loadingLiveEvent = MutableLiveData<Boolean>()
    val loadingLiveData: LiveData<Boolean> get() = _loadingLiveEvent

    private val compositeDisposable = CompositeDisposable()

    @CallSuper
    override fun onCleared() {
        compositeDisposable.clear()
    }

    protected fun disposeOnCleared(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    protected fun showLoading() {
        _loadingLiveEvent.value = true
    }

    protected fun hideLoading() {
        _loadingLiveEvent.value = false
    }

    protected fun processError(error: Throwable) {
        when (error) {
            is IllegalArgumentException -> {
                _errorLiveEvent.setValue(Unit)
            }
            else -> _unknownErrorLiveEvent.setValue(Unit)
        }
    }
}
