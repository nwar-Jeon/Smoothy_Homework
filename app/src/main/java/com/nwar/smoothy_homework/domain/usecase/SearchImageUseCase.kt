package com.nwar.smoothy_homework.domain.usecase

import com.nwar.smoothy_homework.data.cache.FavoritesCache
import com.nwar.smoothy_homework.domain.entity.SearchImage
import com.nwar.smoothy_homework.data.mapper.toSearchImage
import com.nwar.smoothy_homework.data.remote.createAPI
import com.nwar.smoothy_homework.app.scheduler.SchedulerStorage
import com.nwar.smoothy_homework.data.cache.FavoritesCacheObject
import com.nwar.smoothy_homework.util.getBodyOrThrow
import io.reactivex.rxjava3.core.Flowable

class SearchImageUseCase(scheduler : SchedulerStorage) : UseCase<Pair<Int, String>, SearchImage>(scheduler) {

    private val api = createAPI()

    private val token : String = "KakaoAK 43e1b3442105123c20d3d4b0db51eb35"

    private val favoriteCache : FavoritesCache<SearchImage> = FavoritesCacheObject

    private val Pair<Int,String>.page get() = this.first
    private val Pair<Int,String>.searchText get() = this.second

    override fun requestData(data: Pair<Int, String>): Flowable<SearchImage> {
        return api.searchImageList(token, data.searchText, data.page,80)
            .map { it.getBodyOrThrow() }
            .flatMapIterable { it.imageList }
            .map { it.toSearchImage() }
            .doOnNext { if (favoriteCache.contains(it)) it.toggleFavorite() }
    }
}