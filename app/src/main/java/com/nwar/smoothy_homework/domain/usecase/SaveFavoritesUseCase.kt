package com.nwar.smoothy_homework.domain.usecase

import com.nwar.smoothy_homework.app.context.ApplicationContextObject
import com.nwar.smoothy_homework.app.scheduler.SchedulerStorage
import com.nwar.smoothy_homework.data.cache.FavoritesCache
import com.nwar.smoothy_homework.data.cache.FavoritesCacheObject
import com.nwar.smoothy_homework.data.local.LocalStorage
import com.nwar.smoothy_homework.data.local.Storage
import com.nwar.smoothy_homework.domain.entity.SearchImage
import io.reactivex.rxjava3.core.Flowable

class SaveFavoritesUseCase(schedulerStorage: SchedulerStorage) : UseCase<Unit, Unit>(schedulerStorage) {

    private val favoritesCache : FavoritesCache<SearchImage> = FavoritesCacheObject
    private val localStorage : Storage<Collection<SearchImage>> = LocalStorage(ApplicationContextObject.getContext())

    override fun requestData(data: Unit): Flowable<Unit> {
        localStorage.save(favoritesCache.getCollection())
        return Flowable.just(Unit)
    }
}