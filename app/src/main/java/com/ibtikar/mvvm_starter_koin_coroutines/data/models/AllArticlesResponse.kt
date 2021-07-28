package com.ibtikar.mvvm_starter_koin_coroutines.data.models

/**
 * Created by Meslmawy on 6/10/2021
 */

data class AllArticlesResponse(
    val status: String,
    val copyright: String,
    val num_results: Int,
    val results: List<ArticleModelResponse>?
)