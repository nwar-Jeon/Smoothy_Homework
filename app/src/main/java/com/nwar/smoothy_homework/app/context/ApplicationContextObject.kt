package com.nwar.smoothy_homework.app.context

import android.app.Application
import android.content.Context

object ApplicationContextObject : ApplicationContext{
    private lateinit var application : Application

    override fun getContext() = application as Context

    override fun getApplication() = application

    override fun setApplication(application : Application) { this.application = application }
}