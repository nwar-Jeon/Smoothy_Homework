package com.nwar.smoothy_homework.domain.usecase

import com.nwar.smoothy_homework.app.context.ApplicationContextObject
import com.nwar.smoothy_homework.app.scheduler.SchedulerStorage
import com.nwar.smoothy_homework.data.cache.FavoritesCache
import com.nwar.smoothy_homework.data.cache.FavoritesCacheObject
import com.nwar.smoothy_homework.data.local.LocalStorage
import com.nwar.smoothy_homework.data.local.Storage
import com.nwar.smoothy_homework.domain.entity.SearchImage
import io.reactivex.rxjava3.core.Flowable

class LoadFavoritesUseCase(schedulerStorage: SchedulerStorage) : UseCase<Unit, SearchImage>(schedulerStorage) {

    private val storage : Storage<Collection<SearchImage>> = LocalStorage(ApplicationContextObject.getContext())
    private val favoriteCache : FavoritesCache<SearchImage> = FavoritesCacheObject

    override fun requestData(data: Unit): Flowable<SearchImage> {
        return storage.load().flatMapIterable { it }
            .doOnNext { favoriteCache.add(it) }
    }
}