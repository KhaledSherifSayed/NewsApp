package com.ibtikar.mvvm_starter_koin_coroutines.ui.newsList

import com.ibtikar.mvvm_starter_koin_coroutines.data.local.ArticleEntity
import com.ibtikar.mvvm_starter_koin_coroutines.data.models.AllNewsResponse
import com.ibtikar.mvvm_starter_koin_coroutines.data.models.NewsModelResponse
import com.ibtikar.mvvm_starter_koin_coroutines.ui.base.BaseViewModel
import com.ibtikar.mvvm_starter_koin_coroutines.utils.coroutines.ContextProviders
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.supervisorScope

/**
 * Created by Meslmawy on 6/10/2021
 */

class NewsViewModel(
    private val contextProviders: ContextProviders,
    private val newsRepository: NewsRepository
) :
    BaseViewModel(contextProviders) {

    fun getMostSharedArticles(country: String, cats: List<String>) {
        val allResults: ArrayList<NewsModelResponse> = ArrayList()
        val tasks: ArrayList<Deferred<Flow<AllNewsResponse>>> = ArrayList()

        launchBlock(showLoading = true) {
            supervisorScope {

                for (category in cats) {
                    tasks.add(async { newsRepository.getArticleList(country, category) })
                }

                (cats.indices).forEach { i ->
                    tasks[i].await().collect {
                        allResults.addAll(it.articles!!)
                    }
                }
                allResults.sortByDescending { it.publishedDate }
                setState(NewsViewState.onNewsResponse(allResults))
            }
        }
    }

    fun addArticleToFavorite(article: ArticleEntity) {
        launchBlock(showLoading = true) {
            newsRepository.checkItem(article).collect {
                if (it)
                    setState(NewsViewState.onAddingFavoriteResponse(false))
                else
                    newsRepository.addArticleToFavoriteList(article).collect {
                        setState(NewsViewState.onAddingFavoriteResponse(true))
                    }
            }
        }
    }

}
