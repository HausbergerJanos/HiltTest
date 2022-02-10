package com.application.hilttest

import android.app.Application
import androidx.test.core.app.ApplicationProvider

fun <T : Application> application(): T = ApplicationProvider.getApplicationContext() as T