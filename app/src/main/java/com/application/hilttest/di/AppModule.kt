package com.application.hilttest.di

import android.app.Application
import android.content.Context
import com.application.hilttest.MyApp
import com.application.hilttest.counter.Counter
import com.application.hilttest.counter.CounterImpl
import com.application.hilttest.state.CounterViewModelImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(
    includes = [
        AppModule.DelegateBindings::class
    ]
)
class AppModule {

    @Module
    interface DelegateBindings {

        @Binds
        @Singleton
        fun provideApplication(delegate: MyApp): Application

        @Binds
        @Singleton
        fun provideContext(delegate: Application): Context

    }

    @Provides
    @Singleton
    fun provideCounter(): Counter = CounterImpl()

//    @Provides
//    @Named(APP_IS_IN_DEBUG)
//    fun appIsInDebug(): Boolean = BuildConfig.DEBUG
//
//    @Provides
//    @Singleton
//    fun providePackageInfo(context: Context): PackageInfo =
//        context.packageManager.getPackageInfo(context.packageName, 0)
//
//    @Provides
//    @Named(APP_VERSION_NAME)
//    @Singleton
//    fun provideAppVersionName(packageInfo: PackageInfo): String = packageInfo.versionName
//
//    @Provides
//    @Named(APP_VERSION_CODE)
//    @Singleton
//    fun provideAppVersionCode(packageInfo: PackageInfo): Long =
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
//            packageInfo.longVersionCode
//        } else {
//            @Suppress("DEPRECATION")
//            packageInfo.versionCode.toLong()
//        }
//
//    @Provides
//    @Named(APP_VERSION_NUMBER)
//    @Singleton
//    fun provideAppVersionNumber(
//        @Named(APP_VERSION_NAME) versionName: String,
//        @Named(APP_VERSION_CODE) versionCode: Long,
//    ): String = "$versionName+$versionCode"
//
//    @Provides
//    fun provideFirebaseAnalytics() = Firebase.analytics
//
//    @Provides
//    fun provideFirebaseCrashlytics() = FirebaseCrashlytics.getInstance()
}
