package com.nwar.smoothy_homework.domain.usecase

import com.nwar.smoothy_homework.app.scheduler.SchedulerStorage
import com.nwar.smoothy_homework.data.cache.DetailImageCache
import com.nwar.smoothy_homework.data.cache.DetailImageCacheObject
import com.nwar.smoothy_homework.domain.entity.SearchImage
import io.reactivex.rxjava3.core.Flowable

class SaveImageUseCase(schedulerStorage: SchedulerStorage) : UseCase<SearchImage, Unit>(schedulerStorage) {

    private val detailImageCache : DetailImageCache = DetailImageCacheObject

    override fun requestData(data: SearchImage): Flowable<Unit> {
        return detailImageCache.setImage(data)
    }
}