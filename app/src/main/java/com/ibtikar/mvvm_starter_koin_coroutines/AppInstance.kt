package com.ibtikar.mvvm_starter_koin_coroutines

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import android.util.Log
import androidx.annotation.CallSuper
import com.ibtikar.mvvm_starter_koin_coroutines.di.*
import com.ibtikar.mvvm_starter_koin_coroutines.utils.LocaleHelper
import com.ihsanbal.logging.LoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.logger.Level


class AppInstance : Application() {

    lateinit var koin: KoinApplication private set

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            LoggingInterceptor.Builder()
                .logger(object : com.ihsanbal.logging.Logger {
                    override fun log(level: Int, tag: String?, msg: String?) {
                        Log.e("$tag - $level", "$msg")
                    }
                })
        }
        intiKoin()
    }

    private fun intiKoin() {
        koin = startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@AppInstance)
            modules(
                listOf(
                    generalModule,
                    DBModule,
                    repositoryModule,
                    viewModelModule,
                    contextProviderModule,
                    retrofitModule,
                    apiModule,
                    sharedPreferencesModule
                )
            )
        }
    }

    @CallSuper
    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(LocaleHelper.setLocale(newBase!!))
    }

    @CallSuper
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        LocaleHelper.setLocale(this)
    }
}