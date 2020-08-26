package com.nwar.smoothy_homework.domain.usecase

import com.nwar.smoothy_homework.app.scheduler.SchedulerStorage
import com.nwar.smoothy_homework.data.cache.DetailImageCache
import com.nwar.smoothy_homework.data.cache.DetailImageCacheObject
import com.nwar.smoothy_homework.domain.entity.SearchImage
import io.reactivex.rxjava3.core.Flowable

class LoadImageUseCase(schedulerStorage: SchedulerStorage) : UseCase<Unit, SearchImage>(schedulerStorage) {

    private val detailImageCache : DetailImageCache = DetailImageCacheObject

    override fun requestData(data: Unit): Flowable<SearchImage> {
        return detailImageCache.getImage()
    }
}