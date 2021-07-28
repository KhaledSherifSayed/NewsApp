package com.ibtikar.mvvm_starter_koin_coroutines.ui.articlesList

import com.ibtikar.mvvm_starter_koin_coroutines.ui.base.BaseViewModel
import com.ibtikar.mvvm_starter_koin_coroutines.ui.base.ViewState
import com.ibtikar.mvvm_starter_koin_coroutines.utils.coroutines.ContextProviders
import kotlinx.coroutines.flow.collect

/**
 * Created by Meslmawy on 6/10/2021
 */

class ArticlesViewModel(
    private val contextProviders: ContextProviders,
    private val articlesRepository: ArticlesRepository
) :
    BaseViewModel(contextProviders) {

    fun getMostSharedArticles() {
        launchBlock(showLoading = true) {
            articlesRepository.getArticleList().collect {
                if (it.results?.isNotEmpty() == true)
                    setState(ArticlesViewState.onArticlesResponse(it.results))
                else
                    setState(ViewState.Empty)
            }
        }
    }

}
