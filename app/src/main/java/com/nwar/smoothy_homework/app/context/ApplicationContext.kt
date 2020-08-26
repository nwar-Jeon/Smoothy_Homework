package com.nwar.smoothy_homework.app.context

import android.app.Application
import android.content.Context

interface ApplicationContext {
    fun getContext() : Context

    fun getApplication() : Application

    fun setApplication(application : Application)
}