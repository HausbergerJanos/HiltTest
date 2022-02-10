package com.application.hilttest.state

import androidx.lifecycle.viewModelScope
import com.application.hilttest.counter.Counter
import com.application.hilttest.state.CounterViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class CounterViewModelImpl(
    val counter: Counter
) : CounterViewModel() {

    override fun getCount(): StateFlow<String> {
        return counter.counter.map {
            it.toString()
        }.stateIn(viewModelScope, started = SharingStarted.Lazily, initialValue = "0")
    }

    override fun increase() {
        counter.increase()
    }

    override fun decrease() {
        counter.decrease()
    }
}