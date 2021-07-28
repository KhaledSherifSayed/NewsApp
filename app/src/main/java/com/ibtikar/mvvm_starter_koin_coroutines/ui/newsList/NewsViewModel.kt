package com.ibtikar.mvvm_starter_koin_coroutines.ui.newsList

import com.ibtikar.mvvm_starter_koin_coroutines.ui.base.BaseViewModel
import com.ibtikar.mvvm_starter_koin_coroutines.ui.base.ViewState
import com.ibtikar.mvvm_starter_koin_coroutines.utils.coroutines.ContextProviders
import kotlinx.coroutines.flow.collect

/**
 * Created by Meslmawy on 6/10/2021
 */

class NewsViewModel(
    private val contextProviders: ContextProviders,
    private val newsRepository: NewsRepository
) :
    BaseViewModel(contextProviders) {

    fun getMostSharedArticles() {
        launchBlock(showLoading = true) {
            newsRepository.getArticleList().collect {
                if (it.totalResults > 0)
                    setState(NewsViewState.onNewsResponse(it.articles))
                else
                    setState(ViewState.Empty)
            }
        }
    }

}
