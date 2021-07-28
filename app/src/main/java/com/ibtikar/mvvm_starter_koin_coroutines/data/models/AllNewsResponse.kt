package com.ibtikar.mvvm_starter_koin_coroutines.data.models

/**
 * Created by Meslmawy on 6/10/2021
 */

data class AllNewsResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<NewsModelResponse>?
)