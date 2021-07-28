package com.ibtikar.mvvm_starter_koin_coroutines.data.network.interceptor


import com.ibtikar.mvvm_starter_koin_coroutines.data.network.annotation.UserAuthentication
import com.ibtikar.mvvm_starter_koin_coroutines.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import retrofit2.Invocation


class TokenInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val newRequest = request.tag(Invocation::class.java)?.let { invocation ->
            val builder = request.newBuilder()

            with(invocation.method()) {
                return@let when {
                    getAnnotation(UserAuthentication::class.java) != null -> builder.also {
                        builder.addHeader("X-Api-Key", BuildConfig.API_KEY)

                    }.build()

                    else -> builder.build()
                }
            }
        }!!

        return chain.proceed(newRequest)
    }
}