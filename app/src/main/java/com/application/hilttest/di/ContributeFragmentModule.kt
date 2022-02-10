package com.application.hilttest.di

import com.application.hilttest.view.CounterFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ContributeFragmentModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun contributeLoginFragment(): CounterFragment
}