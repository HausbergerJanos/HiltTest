package com.application.hilttest.di

import com.application.hilttest.MyApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Scope
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ContributeFragmentModule::class,
        AppModule::class
    ]
)
interface AppComponent : AndroidInjector<MyApp> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: MyApp): AndroidInjector<MyApp>
    }
}

@Scope
@MustBeDocumented
@Retention
annotation class ActivityScope

@Scope
@MustBeDocumented
@Retention
annotation class FragmentScope