package com.nwar.smoothy_homework.util

import retrofit2.Response
import java.lang.RuntimeException

fun <T>Response<T>.getBodyOrThrow() : T {
    when(this.code() / 100) {
        2 -> return this.body()!!
        in 3..5 -> throw RuntimeException("${this.code()} code Error!")
        else -> throw RuntimeException("Unknown Error!")
    }
}