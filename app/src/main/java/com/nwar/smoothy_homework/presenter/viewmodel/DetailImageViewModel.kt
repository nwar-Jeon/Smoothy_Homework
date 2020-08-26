package com.nwar.smoothy_homework.presenter.viewmodel

import androidx.lifecycle.MutableLiveData
import com.nwar.smoothy_homework.app.scheduler.AndroidSchedulerStorage
import com.nwar.smoothy_homework.domain.entity.SearchImage
import com.nwar.smoothy_homework.domain.usecase.FavoriteClickUseCase
import com.nwar.smoothy_homework.domain.usecase.LoadImageUseCase
import io.reactivex.rxjava3.subscribers.DisposableSubscriber

class DetailImageViewModel : BaseViewModel() {

    val content = MutableLiveData<SearchImage>()

    private val favoriteClickUseCase = FavoriteClickUseCase(AndroidSchedulerStorage)
    private val loadImageUseCase = LoadImageUseCase(AndroidSchedulerStorage)

    override fun registerUseCase() {
        registerUseCases(favoriteClickUseCase, loadImageUseCase)
    }

    override fun onCreate() {
        super.onCreate()
        loadImageUseCase.excute(Unit, loadImageSubscriber)
    }

    fun requestClickFavorite() {
        content.value ?: return
        favoriteClickUseCase.excute(0 to content.value!!, favoriteClickSubscriber)
    }

    fun backPressed() {
        backNavigate.postValue(true)
    }

    private val favoriteClickSubscriber get() = object : DisposableSubscriber<Int>() {
        override fun onComplete() {
            val item = content.value!!
            item.toggleFavorite()
            content.postValue(item)
        }

        override fun onNext(t: Int?) {}

        override fun onError(t: Throwable?) { toast.postValue(t?.message) }
    }

    private val loadImageSubscriber get() = object : DisposableSubscriber<SearchImage>() {
        override fun onComplete() {}

        override fun onNext(t: SearchImage?) {
            t ?: return
            content.postValue(t)
        }

        override fun onError(t: Throwable?) { toast.postValue(t?.message) }
    }
}