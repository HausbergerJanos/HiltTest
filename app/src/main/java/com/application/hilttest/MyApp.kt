package com.application.hilttest

import com.application.hilttest.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class MyApp : DaggerApplication(){

    private val appComponent: AndroidInjector<MyApp> by lazy {
        DaggerAppComponent
            .factory()
            .create(this)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = appComponent
}