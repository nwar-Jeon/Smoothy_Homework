package com.nwar.smoothy_homework.data.cache

import com.nwar.smoothy_homework.domain.entity.SearchImage
import io.reactivex.rxjava3.core.Flowable

interface DetailImageCache {
    fun getImage() : Flowable<SearchImage>

    fun setImage(item : SearchImage?) : Flowable<Unit>

    fun clear() : Flowable<Unit>
}