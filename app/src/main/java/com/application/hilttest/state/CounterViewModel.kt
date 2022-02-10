package com.application.hilttest.state

import kotlinx.coroutines.flow.StateFlow

interface CounterViewModel {
    fun increase()
    fun decrease()
    fun getCount(): StateFlow<String>
}