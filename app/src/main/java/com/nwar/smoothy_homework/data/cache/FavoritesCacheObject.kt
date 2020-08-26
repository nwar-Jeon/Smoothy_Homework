package com.nwar.smoothy_homework.data.cache

import android.util.Log
import com.nwar.smoothy_homework.domain.entity.SearchImage

object FavoritesCacheObject : FavoritesCache<SearchImage> {
    private val favorites : MutableSet<SearchImage> = mutableSetOf()

    override fun add(item : SearchImage) = favorites.add(item)

    override fun addAll(items : Collection<SearchImage>) = favorites.addAll(items)

    override fun remove(item : SearchImage) = favorites.remove(item)

    override fun removeAll(items : Collection<SearchImage>) = favorites.removeAll(items)

    override fun contains(item : SearchImage) = favorites.contains(item)

    override fun getCollection(): Collection<SearchImage> = favorites

    override fun clear() = favorites.clear()
}