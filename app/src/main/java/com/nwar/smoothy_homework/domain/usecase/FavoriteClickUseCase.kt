package com.nwar.smoothy_homework.domain.usecase

import com.nwar.smoothy_homework.data.cache.FavoritesCache
import com.nwar.smoothy_homework.domain.entity.SearchImage
import com.nwar.smoothy_homework.app.scheduler.SchedulerStorage
import com.nwar.smoothy_homework.data.cache.FavoritesCacheObject
import io.reactivex.rxjava3.core.Flowable

class FavoriteClickUseCase(scheduler: SchedulerStorage) : UseCase<Pair<Int, SearchImage>, Int>(scheduler) {
    private val Pair<Int,SearchImage>.position get() = this.first
    private val Pair<Int,SearchImage>.searchImage get() = this.second

    val favoriteCache : FavoritesCache<SearchImage> = FavoritesCacheObject

    override fun requestData(data: Pair<Int,SearchImage>): Flowable<Int> {
        if(data.searchImage.isFavorite) favoriteCache.remove(data.searchImage)
        else favoriteCache.add(data.searchImage)

        return Flowable.just(data.position)
    }
}