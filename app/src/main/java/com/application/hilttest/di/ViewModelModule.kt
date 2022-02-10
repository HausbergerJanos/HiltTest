package com.application.hilttest.di

import androidx.lifecycle.ViewModelProvider
import com.application.hilttest.state.CounterViewModelFactory
import com.application.hilttest.counter.Counter
import com.application.hilttest.counter.CounterImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
object ViewModelModule {

    @Provides
    fun provideCounter(): Counter {
        return CounterImpl()
    }


    @Provides
    fun provideCounterViewModelFactory(
        counter: Counter
    ): ViewModelProvider.Factory {
        return CounterViewModelFactory(counter)
    }
}