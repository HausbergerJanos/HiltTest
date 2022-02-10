package com.application.hilttest.di

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.application.hilttest.state.CounterViewModel
import com.application.hilttest.state.CounterViewModelImpl
import com.application.hilttest.view.CounterFragment
import dagger.Module
import dagger.Provides
import javax.inject.Provider

inline fun <Activity : FragmentActivity, reified ViewModelImpl : ViewModel> createOrReUseViewModel(
    activity: Activity,
    provider: Provider<ViewModelImpl>,
): ViewModelImpl {
    return ViewModelProvider(activity, object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T = provider.get() as T
    })[ViewModelImpl::class.java]
}

inline fun <Frag : Fragment, reified ViewModelImpl : ViewModel> createOrReUseViewModel(
    fragment: Frag,
    provider: Provider<ViewModelImpl>,
): ViewModelImpl {
    return ViewModelProvider(fragment, object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>) = provider.get() as T
    })[ViewModelImpl::class.java]
}

@Module
object ViewModelModule {

    @Provides
    fun provideCounterViewModel(
        fragment: CounterFragment,
        provider: Provider<CounterViewModelImpl>,
    ): CounterViewModel {
        return createOrReUseViewModel(fragment, provider)
    }
}