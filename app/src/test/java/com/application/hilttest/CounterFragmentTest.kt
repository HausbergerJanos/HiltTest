package com.application.hilttest

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.fragment.app.testing.withFragment
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.application.hilttest.state.CounterViewModel
import com.application.hilttest.view.CounterFragment
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(
    sdk = [30],
    application = CounterFragmentTest.TestApplication::class,
    instrumentedPackages = [
        // required to access final members on androidx.loader.content.ModernAsyncTask
        "androidx.loader.content"
    ]
)
class CounterFragmentTest {

    class TestApplication : InjectingTestApplication() {

        val viewModel: CounterViewModel = mockk(relaxed = true)

        override fun inject(instance: Any?) {
            (instance as CounterFragment).viewModel = viewModel
        }
    }

    private val mockViewModel = application<TestApplication>().viewModel

    @Test
    fun `GIVEN CounterFragment WHEN created THEN show zero count`() {
        every { mockViewModel.getCount() } returns MutableStateFlow("2")
        launchFragmentInContainer<CounterFragment>(themeResId = R.style.Theme_HiltTest).use {
            it.withFragment {
                onView(withId(R.id.counter_label)).check(matches(withText("2")))
            }
        }
    }

    @Test
    fun `GIVEN CounterFragment WHEN created THEN has increase and decrease button`() {
        launchFragmentInContainer<CounterFragment>(themeResId = R.style.Theme_HiltTest).use {
            it.withFragment {
                Assert.assertEquals(getString(R.string.decrease),
                    binding.decreaseButton.text)

                Assert.assertEquals(this.getString(R.string.increase),
                    binding.increaseButton.text)
            }
        }
    }

    @Test
    fun `GIVEN CounterFragment WHEN increase button clicked THEN viewModel's increase method called`() {
        launchFragmentInContainer<CounterFragment>(themeResId = R.style.Theme_HiltTest).use {
            it.withFragment {
                binding.increaseButton.performClick()
                verify {
                    viewModel.increase()
                }
            }
        }
    }

    @Test
    fun `GIVEN CounterFragment WHEN decrease button clicked THEN viewModel's decrease method called`() {
        launchFragmentInContainer<CounterFragment>(themeResId = R.style.Theme_HiltTest).use {
            it.withFragment {
                binding.decreaseButton.performClick()
                verify {
                    viewModel.decrease()
                }
            }
        }
    }
}