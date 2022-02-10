package com.application.hilttest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.application.hilttest.view.CounterFragment
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import io.mockk.coEvery
import io.mockk.every
import io.mockk.verify
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
@Config(
    sdk = [30],
    application = HiltTestApplication::class,
    instrumentedPackages = [
        // required to access final members on androidx.loader.content.ModernAsyncTask
        "androidx.loader.content"
    ]
)
class CounterFragmentTest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun `GIVEN CounterFragment WHEN created THEN show zero count`() = runTest {
        launchFragmentInHiltContainer<CounterFragment> {
            launch {
                every { viewModel.getCount() } returns MutableStateFlow("0")
                val count = viewModel.getCount().first()
                //binding.counterLabel.text = count
                onView(withId(R.id.counter_label)).check(matches(withText("0")))
            }
        }
    }

    @Test
    fun `GIVEN CounterFragment WHEN created THEN has increase and decrease button`() {
        launchFragmentInHiltContainer<CounterFragment> {
            Assert.assertEquals(this.getString(R.string.decrease),
                binding.decreaseButton.text)

            Assert.assertEquals(this.getString(R.string.increase),
                binding.increaseButton.text)
        }
    }

    @Test
    fun `GIVEN CounterFragment WHEN increase button clicked THEN viewModel's increase method called`() {
        launchFragmentInHiltContainer<CounterFragment> {
            onView(withText(R.string.increase)).perform(ViewActions.click())
            verify {
                viewModel.increase()
            }
        }
    }

    @Test
    fun `GIVEN CounterFragment WHEN decrease button clicked THEN viewModel's decrease method called`() {
        launchFragmentInHiltContainer<CounterFragment> {
            onView(withText(R.string.decrease)).perform(ViewActions.click())
            verify {
                viewModel.decrease()
            }
        }
    }
}