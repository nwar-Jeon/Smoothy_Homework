package com.nwar.smoothy_homework.data.local

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nwar.smoothy_homework.domain.entity.SearchImage
import io.reactivex.rxjava3.core.Flowable

class LocalStorage(context : Context) : Storage<Collection<SearchImage>> {
    private val pref : SharedPreferences = context.getSharedPreferences("favorites", Context.MODE_PRIVATE)
    private val gson = Gson()
    private val contentName = "favorites"

    override fun load() : Flowable<Collection<SearchImage>> {
        val favorites = gson.fromJson<List<SearchImage>>(getStringFromPref(), object : TypeToken<List<SearchImage>>(){}.type)
        return if(favorites.isNullOrEmpty()) Flowable.error<Collection<SearchImage>>(RuntimeException("즐겨찾기 설정한 항목이 없음.")) else Flowable.just(favorites)
    }

    override fun save(favorites : Collection<SearchImage>) : Flowable<Unit> {
        pref.edit().apply {
            clear()
            putStringToPref(objectToJson(favorites))
            apply()
        }
        return Flowable.just(Unit)
    }

    private fun getStringFromPref() = pref.getString(contentName, "") ?: ""

    private fun SharedPreferences.Editor.putStringToPref(string : String) = this.putString(contentName, string)

    private fun <T>jsonToObject(json : String) = gson.fromJson<T>(json, object : TypeToken<T>(){}.type)

    private fun objectToJson(item : Any?) = gson.toJson(item)
}