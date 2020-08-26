package com.nwar.smoothy_homework.data.cache

import com.nwar.smoothy_homework.domain.entity.SearchImage

interface FavoritesCache<T> {
    fun add(item : T) : Boolean

    fun addAll(items : Collection<T>): Boolean

    fun remove(item : T): Boolean

    fun removeAll(items : Collection<T>): Boolean

    fun contains(item : T): Boolean

    fun getCollection() : Collection<T>

    fun clear()
}