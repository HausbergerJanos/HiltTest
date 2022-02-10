package com.application.hilttest.counter

import kotlinx.coroutines.flow.StateFlow

interface Counter {
    val counter: StateFlow<Int>
    fun increase()
    fun decrease()
}