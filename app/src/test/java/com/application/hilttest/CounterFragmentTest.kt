package com.application.hilttest

import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import org.junit.Assert
import org.junit.Assert.*
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

    @get:Rule()
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun testFragment() {
        launchFragmentInHiltContainer<CounterFragment> {
            Assert.assertTrue(true)
        }
    }
}