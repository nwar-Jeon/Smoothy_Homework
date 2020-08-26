package com.nwar.smoothy_homework.domain.usecase

import com.nwar.smoothy_homework.app.scheduler.SchedulerStorage
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.subscribers.DisposableSubscriber

abstract class UseCase<T,R>(val scheduler : SchedulerStorage) {

    val compositeDisposable = CompositeDisposable()

    fun excute(data : T, disposable : DisposableSubscriber<R>) {
        val flowable = requestData(data)
            .observeOn(scheduler.mainScheduler())
            .subscribeOn(scheduler.io())
            .subscribeWith(disposable)
        compositeDisposable.add(flowable)
    }

    abstract fun requestData(data : T) : Flowable<R>

    fun clear() = compositeDisposable.clear()
}