package com.nwar.smoothy_homework.data.cache

import com.nwar.smoothy_homework.domain.entity.SearchImage
import io.reactivex.rxjava3.core.Flowable

object DetailImageCacheObject: DetailImageCache {

    private var imageSource : SearchImage? = null

    override fun clear(): Flowable<Unit> {
        return setImage(null)
    }

    override fun getImage(): Flowable<SearchImage> {
        return Flowable.just(imageSource)
    }

    override fun setImage(item: SearchImage?): Flowable<Unit> {
        imageSource = item
        return Flowable.just(Unit)
    }
}