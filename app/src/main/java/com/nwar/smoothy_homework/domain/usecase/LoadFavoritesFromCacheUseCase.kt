package com.nwar.smoothy_homework.domain.usecase

import com.nwar.smoothy_homework.app.scheduler.SchedulerStorage
import com.nwar.smoothy_homework.data.cache.FavoritesCache
import com.nwar.smoothy_homework.data.cache.FavoritesCacheObject
import com.nwar.smoothy_homework.domain.entity.SearchImage
import io.reactivex.rxjava3.core.Flowable

class LoadFavoritesFromCacheUseCase(schedulerStorage: SchedulerStorage) : UseCase<Unit, SearchImage>(schedulerStorage) {

    private val favoritesCache : FavoritesCache<SearchImage> = FavoritesCacheObject

    override fun requestData(data: Unit): Flowable<SearchImage> {
        return Flowable.just(favoritesCache.getCollection())
            .flatMapIterable { it }
    }
}