package com.application.hilttest.counter

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class CounterTest {

    lateinit var counter: Counter

    @Before
    fun init() {
        counter = CounterImpl()
    }

    @Test
    fun `GIVEN Counter WHEN collected first emit 0 value`() = runTest {
        val currentValue = counter.counter.first()
        Assert.assertEquals(0, currentValue)
    }

    @Test
    fun `GIVEN Counter WHEN increase called emit 1 value`() = runTest {
        counter.increase()
        val currentValue = counter.counter.first()
        Assert.assertEquals(1, currentValue)
    }

    @Test
    fun `GIVEN Counter WHEN decrease called set value to 2 emit 1 value`() = runTest {
        val counter = CounterImpl(2)
        counter.decrease()
        val currentValue = counter.counter.first()
        Assert.assertEquals(1, currentValue)
    }

    @Test
    fun `GIVEN Counter WHEN decrease called at 0 not emit negative value`() = runTest {
        val counter = CounterImpl()
        counter.decrease()
        val currentValue = counter.counter.first()
        Assert.assertEquals(0, currentValue)
    }
}