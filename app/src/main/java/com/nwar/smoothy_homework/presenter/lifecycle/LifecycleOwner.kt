package com.nwar.smoothy_homework.presenter.lifecycle

import androidx.lifecycle.Lifecycle

class LifecycleOwner {
    val listenerList = mutableListOf<LifecycleListener>()

    fun register(listener: LifecycleListener) = listenerList.add(listener)

    fun event(event : Lifecycle.Event) = listenerList.forEach { it.apply(event) }

    fun unRegister(listener: LifecycleListener) = listenerList.remove(listener)
}