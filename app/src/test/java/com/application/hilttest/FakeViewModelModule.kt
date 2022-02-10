package com.application.hilttest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.application.hilttest.counter.Counter
import com.application.hilttest.di.ViewModelModule
import com.application.hilttest.state.CounterViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.testing.TestInstallIn
import io.mockk.mockk

@Module
@TestInstallIn(
    components = [FragmentComponent::class],
    replaces = [ViewModelModule::class]
)
object FakeViewModelModule {

    @Provides
    fun provideCounter(): Counter {
        return mockk<Counter>(relaxed = true)
    }

    @Provides
    fun provideCounterViewModelFactory(
        counter: Counter
    ): ViewModelProvider.Factory {
        return ViewModelFactory(counter)
    }

    class ViewModelFactory(counter: Counter) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return mockk<CounterViewModel>(relaxed = true) as T
        }
    }
}