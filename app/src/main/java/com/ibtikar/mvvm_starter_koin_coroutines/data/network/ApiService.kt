package com.ibtikar.mvvm_starter_koin_coroutines.data.network

import com.ibtikar.mvvm_starter_koin_coroutines.data.network.annotation.UserAuthentication
import com.ibtikar.mvvm_starter_koin_coroutines.data.models.AllNewsResponse
import com.ibtikar.mvvm_starter_koin_coroutines.data.network.APIS.URL.NEWS.URL_GET_NEWS
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Meslmawy on 6/10/2021
 */

interface ApiService {
    @UserAuthentication
    @GET(URL_GET_NEWS)
    suspend fun getAllArticles(@Query(value = "country") period: String?="us"): AllNewsResponse
}