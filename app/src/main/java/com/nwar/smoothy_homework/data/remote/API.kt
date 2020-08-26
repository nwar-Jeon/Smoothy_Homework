package com.nwar.smoothy_homework.data.remote

import com.nwar.smoothy_homework.data.remote.model.SearchImageList
import io.reactivex.rxjava3.core.Flowable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface API {

    @GET("/v2/search/image")
    fun searchImageList(@Header("Authorization")token : String, @Query("query")searchText : String, @Query("page")page : Int = 1, @Query("size")size : Int = 80) : Flowable<Response<SearchImageList>>

}