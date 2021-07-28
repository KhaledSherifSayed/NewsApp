package com.ibtikar.mvvm_starter_koin_coroutines.ui.newsList

import com.ibtikar.mvvm_starter_koin_coroutines.data.models.AllNewsResponse
import com.ibtikar.mvvm_starter_koin_coroutines.data.models.NewsModelResponse
import com.ibtikar.mvvm_starter_koin_coroutines.ui.base.BaseViewModel
import com.ibtikar.mvvm_starter_koin_coroutines.utils.coroutines.ContextProviders
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

/**
 * Created by Meslmawy on 6/10/2021
 */

class NewsViewModel(
    private val contextProviders: ContextProviders,
    private val newsRepository: NewsRepository
) :
    BaseViewModel(contextProviders) {

    fun getMostSharedArticles(country: String, cats: List<String>) {
        var allResults: ArrayList<NewsModelResponse> = ArrayList()
        var tasks: ArrayList<Deferred<Flow<AllNewsResponse>>> = ArrayList()

        launchBlock(showLoading = true) {
            for (category in cats) {
                tasks.add(async { newsRepository.getArticleList(country, category) })
            }

            (cats.indices).forEach { i ->
                tasks[i].await().collect {
                    allResults.addAll(it.articles!!)
                }
            }
            allResults.sortByDescending{it.publishedDate}
            setState(NewsViewState.onNewsResponse(allResults))
        }
    }

}
