package com.ibtikar.mvvm_starter_koin_coroutines.ui.newsList


import com.ibtikar.mvvm_starter_koin_coroutines.data.network.ApiService
import com.ibtikar.mvvm_starter_koin_coroutines.ui.base.BaseRepository
import com.ibtikar.mvvm_starter_koin_coroutines.utils.coroutines.ContextProviders

/**
 * Created by Meslmawy on 6/10/2021
 */


class NewsRepository(
    contextProviders: ContextProviders,
    private val apiService: ApiService
) : BaseRepository(contextProviders) {

    fun getArticleList(country : String,category: String) = networkHandler {
        apiService.getAllArticles(country,category)
    }
}
