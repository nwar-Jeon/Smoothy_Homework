package com.nwar.smoothy_homework.presenter.lifecycle

import androidx.lifecycle.Lifecycle

interface LifecycleListener {
    fun apply(event : Lifecycle.Event)
}