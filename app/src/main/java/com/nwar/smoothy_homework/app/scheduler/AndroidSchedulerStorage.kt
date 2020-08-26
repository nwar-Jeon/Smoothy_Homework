package com.nwar.smoothy_homework.app.scheduler

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers

object AndroidSchedulerStorage : SchedulerStorage {
    override fun io(): Scheduler = Schedulers.io()

    override fun mainScheduler(): Scheduler = AndroidSchedulers.mainThread()
}