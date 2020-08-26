package com.nwar.smoothy_homework

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nwar.smoothy_homework.domain.entity.SearchImage
import com.nwar.smoothy_homework.app.scheduler.SchedulerStorage
import com.nwar.smoothy_homework.domain.usecase.SearchImageUseCase
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subscribers.DisposableSubscriber
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    class JavaSchedulerStorage : SchedulerStorage {
        override fun io(): Scheduler = Schedulers.io()
        override fun mainScheduler(): Scheduler = Schedulers.newThread()
    }

    val disposable get() = object : DisposableSubscriber<SearchImage>() {
        override fun onComplete() {
            println("onComplete")
        }

        override fun onError(t: Throwable?) {
            println(t?.message)
        }

        override fun onNext(t: SearchImage?) {
            println(t?.title + " " + t?.imageURL)
        }
    }
    @Test
    fun addition_isCorrect() {
        val favorites = Gson().fromJson<Collection<SearchImage>>("", object : TypeToken<List<SearchImage>>(){}.type)
        println(favorites)
    }
}