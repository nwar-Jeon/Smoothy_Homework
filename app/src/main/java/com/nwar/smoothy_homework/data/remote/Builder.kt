package com.nwar.smoothy_homework.data.remote

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



fun createAPI() = Retrofit.Builder()
    .baseUrl("https://dapi.kakao.com/")
    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
    .addConverterFactory(GsonConverterFactory.create())
    .build()
    .create(API::class.java)