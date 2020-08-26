package com.nwar.smoothy_homework.app.scheduler

import io.reactivex.rxjava3.core.Scheduler

interface SchedulerStorage {
    fun mainScheduler() : Scheduler

    fun io() : Scheduler
}