package com.cm.base.interactors.base

import com.cm.base.executor.AppRxSchedulers
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.subscribers.DisposableSubscriber

abstract class FlowableUseCase<T, in Params>(
    private val rxSchedulers: AppRxSchedulers
) {

    val disposables = CompositeDisposable()

    abstract fun buildUseCaseObservable(params: Params?): Flowable<T>

    open fun invokeUseCase(
        observer: DisposableSubscriber<T>,
        params: Params?
    ) {
        buildRxSchedulerBasedDisposable(observer, params)
    }

    private fun buildRxSchedulerBasedDisposable(
        observer: DisposableSubscriber<T>,
        params: Params?
    ) {
        addDisposable(
            buildUseCaseObservable(params)
                .subscribeOn(rxSchedulers.io)
                .observeOn(rxSchedulers.main)
                .subscribeWith(observer)
        )
    }

    fun dispose() {
        if (disposables.isDisposed) {
            disposables.dispose()
        }
    }

    fun clear() {
        disposables.clear()
    }

    private fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }
}