package com.application.hilttest.state

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow

abstract class CounterViewModel : ViewModel() {
    //abstract val counterValue: StateFlow<String>
    abstract fun increase()
    abstract fun decrease()
    abstract fun getCount(): StateFlow<String>
}