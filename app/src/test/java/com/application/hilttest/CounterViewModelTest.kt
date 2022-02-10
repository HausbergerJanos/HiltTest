package com.application.hilttest

import com.application.hilttest.counter.Counter
import com.application.hilttest.state.CounterViewModel
import com.application.hilttest.state.CounterViewModelImpl
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.*
import org.junit.*

@ExperimentalCoroutinesApi
class CounterViewModelTest {
    lateinit var counter: Counter

    lateinit var viewModel: CounterViewModel

    private val dispatcher = StandardTestDispatcher()

    @Before
    fun init() {
        counter = mockk<Counter>(relaxed = true)
        viewModel = CounterViewModelImpl(counter)
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `GIVEN CounterViewModel WHEN created THEN collect zero value`() = runTest {
        every { counter.counter } returns MutableStateFlow(0)
        Assert.assertEquals("0", viewModel.getCount().first())
    }

    @Test
    fun `GIVEN CounterViewModel WHEN increase called THEN counter increase method called`() {
        viewModel.increase()
        verify {
            counter.increase()
        }
    }

    @Test
    fun `GIVEN CounterViewModel WHEN decrease called THEN counter decrease method called`() {
        viewModel.decrease()
        verify {
            counter.decrease()
        }
    }
}