package com.nwar.smoothy_homework.presenter.viewmodel

import com.nwar.smoothy_homework.app.scheduler.AndroidSchedulerStorage
import com.nwar.smoothy_homework.domain.entity.SearchImage
import com.nwar.smoothy_homework.domain.usecase.LoadFavoritesUseCase
import com.nwar.smoothy_homework.domain.usecase.SaveFavoritesUseCase
import io.reactivex.rxjava3.subscribers.DisposableSubscriber

class ActivityViewModel : BaseViewModel() {

    private val loadFavoritesUseCase = LoadFavoritesUseCase(AndroidSchedulerStorage)
    private val saveFavoritesUseCase = SaveFavoritesUseCase(AndroidSchedulerStorage)

    override fun registerUseCase() {
        registerUseCases(loadFavoritesUseCase, saveFavoritesUseCase)
    }

    override fun onCreate() {
        super.onCreate()
        loadFavoritesUseCase.excute(Unit, loadSubscriber)
    }

    override fun onDestroy() {
        super.onDestroy()
        saveFavoritesUseCase.excute(Unit, saveSubscriber)
    }

    private val loadSubscriber get() = object : DisposableSubscriber<SearchImage>() {
        override fun onComplete() {}

        override fun onError(t: Throwable?) {}

        override fun onNext(t: SearchImage?) {}
    }

    private val saveSubscriber get() = object : DisposableSubscriber<Unit>() {
        override fun onComplete() {}

        override fun onError(t: Throwable?) {}

        override fun onNext(t: Unit?) {}
    }
}