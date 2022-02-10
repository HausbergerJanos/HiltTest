package com.application.hilttest.counter

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CounterImpl(private val initialValue: Int = 0) : Counter {

    private val _counter: MutableStateFlow<Int> by lazy { MutableStateFlow(initialValue) }
    override val counter: StateFlow<Int>
        get() = _counter.asStateFlow()

    override fun increase() {
        _counter.update {
            it + 1
        }
    }

    override fun decrease() {
        if (counter.value > 0) {
            _counter.update {
                it - 1
            }
        }
    }
}