package com.nwar.smoothy_homework.presenter.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.nwar.smoothy_homework.app.scheduler.AndroidSchedulerStorage
import com.nwar.smoothy_homework.domain.entity.SearchImage
import com.nwar.smoothy_homework.domain.usecase.FavoriteClickUseCase
import com.nwar.smoothy_homework.domain.usecase.LoadFavoritesFromCacheUseCase
import io.reactivex.rxjava3.subscribers.DisposableSubscriber

class FavoriteFragmentViewModel : BaseViewModel() {
    val favorites = mutableListOf<ObservableField<SearchImage>>()
    val favoritesLiveData by lazy { MutableLiveData(favorites) }

    private val loadFavoritesFromCacheUseCase = LoadFavoritesFromCacheUseCase(AndroidSchedulerStorage)
    private val favoriteClickUseCase = FavoriteClickUseCase(AndroidSchedulerStorage)

    override fun registerUseCase() {
        registerUseCases(favoriteClickUseCase, loadFavoritesFromCacheUseCase)
    }

    override fun onResume() {
        super.onResume()
        favorites.clear()
        loadFavoritesFromCacheUseCase.excute(Unit, loadFavoritesSubscriber)
    }

    fun unRegisterFavorite(pos : Int) {
        val item = favorites[pos].get() ?: return
        favoriteClickUseCase.excute(pos to item, favoriteClickSubscriber)
    }

    private val loadFavoritesSubscriber get() = object : DisposableSubscriber<SearchImage>() {
        override fun onComplete() { favoritesLiveData.postValue(favorites) }

        override fun onNext(t: SearchImage?) { if(t!=null) favorites.add(ObservableField(t)) }

        override fun onError(t: Throwable?) { toast.postValue(t?.message) }
    }

    private val favoriteClickSubscriber get() = object : DisposableSubscriber<Int>() {
        override fun onComplete() {}

        override fun onError(t: Throwable?) {}

        override fun onNext(t: Int?) {
            t ?: return
            favorites.removeAt(t)
            favoritesLiveData.postValue(favorites)
        }
    }
}