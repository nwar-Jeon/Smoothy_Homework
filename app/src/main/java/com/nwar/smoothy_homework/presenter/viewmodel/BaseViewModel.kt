package com.nwar.smoothy_homework.presenter.viewmodel

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nwar.smoothy_homework.domain.usecase.UseCase
import com.nwar.smoothy_homework.presenter.lifecycle.LifecycleListener

abstract class BaseViewModel : ViewModel(), LifecycleListener {

    val toast = MutableLiveData<String>("")

    val nextFragment = MutableLiveData<Int>(null)
    val backNavigate = MutableLiveData<Boolean>(false)

    val useCases = mutableSetOf<UseCase<out Any, out Any>>()

    override fun apply(event: Lifecycle.Event) {
        when(event) {
            Lifecycle.Event.ON_CREATE -> onCreate()
            Lifecycle.Event.ON_START -> onStart()
            Lifecycle.Event.ON_RESUME -> onResume()
            Lifecycle.Event.ON_PAUSE -> onPause()
            Lifecycle.Event.ON_STOP -> onStop()
            Lifecycle.Event.ON_DESTROY -> onDestroy()
            else -> {}
        }
    }

    abstract fun registerUseCase()

    protected fun registerUseCases(vararg useCaseVararg : UseCase<out Any, out Any>) {
        useCaseVararg.forEach { useCases.add(it) }
    }

    open fun onCreate(){ backNavigate.postValue(false) }

    open fun onStart() {}

    open fun onResume() {}

    open fun onPause() {
        nextFragment.postValue(null)
        backNavigate.postValue(false)
    }

    open fun onStop() {}

    open fun onDestroy() { useCases.forEach { it.clear() } }
}