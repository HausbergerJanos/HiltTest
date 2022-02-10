package com.application.hilttest.state

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.application.hilttest.counter.Counter

class CounterViewModelFactory(val counter: Counter) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CounterViewModelImpl(counter) as T
    }
}