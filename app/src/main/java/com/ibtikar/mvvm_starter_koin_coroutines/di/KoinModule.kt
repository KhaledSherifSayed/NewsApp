package com.ibtikar.mvvm_starter_koin_coroutines.di


import android.util.Log
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.ibtikar.mvvm_starter_koin_coroutines.BuildConfig
import com.ibtikar.mvvm_starter_koin_coroutines.data.network.ApiService
import com.ibtikar.mvvm_starter_koin_coroutines.utils.ResourcesHandler
import com.ibtikar.mvvm_starter_koin_coroutines.utils.coroutines.ContextProviders
import com.ihsanbal.logging.LoggingInterceptor
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import com.ihsanbal.logging.Level

/**
 * Created by Meslmawy on 6/10/2021
 */

val generalModule = module {
    single { ResourcesHandler(get()) }
}

val contextProviderModule = module {
    single {
        ContextProviders.getInstance()
    }
}

val apiModule = module {
    fun provideUseApi(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
    single { provideUseApi(get()) }
}

val retrofitModule = module {

    fun provideGson(): Gson {
        return GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create()
    }

    fun provideDefaultLoggingInterceptor(): HttpLoggingInterceptor {
        return if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        } else {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
        }
    }


    fun provideLoggingInterceptor() : LoggingInterceptor {
        val logging = LoggingInterceptor.Builder()

        if (BuildConfig.DEBUG)
            logging.setLevel(Level.BASIC)
        else
            logging.setLevel(Level.NONE)

        return logging.log(Log.VERBOSE).build()
    }

    fun provideHttpClient(loggingInterceptor: LoggingInterceptor): OkHttpClient {

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .build()
    }

    fun provideRetrofit(factory: Gson, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(factory))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(client)
            .build()
    }

    single { provideGson() }
    single { provideDefaultLoggingInterceptor() }
    single { provideLoggingInterceptor() }
    single { provideHttpClient(get()) }
    single { provideRetrofit(get(), get()) }
}