package com.nwar.smoothy_homework.data.local

import io.reactivex.rxjava3.core.Flowable

interface Storage<T> {
    fun load() : Flowable<T>

    fun save(items : T) : Flowable<Unit>
}