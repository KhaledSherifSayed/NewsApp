package com.ibtikar.mvvm_starter_koin_coroutines.ui.favorite


import com.ibtikar.mvvm_starter_koin_coroutines.data.models.toArticleViewResponse
import com.ibtikar.mvvm_starter_koin_coroutines.ui.base.BaseViewModel
import com.ibtikar.mvvm_starter_koin_coroutines.utils.coroutines.ContextProviders
import kotlinx.coroutines.flow.collect

/**
 * Created by Meslmawy on 6/10/2021
 */

class FavoriteNewsViewModel(
    contextProviders: ContextProviders,
    private val newsFavoriteRepository: NewsFavoriteRepository
) :
    BaseViewModel(contextProviders) {

    fun getFavoriteArticles() {
        launchBlock {
            newsFavoriteRepository.getArticleList().collect { flow ->
                flow.collect { list ->
                    setState(FavoriteNewsViewState.onNewsResponse(list.map { it.toArticleViewResponse() }))
                }
            }
        }
    }

    fun removeArticleFromFavorite(id: String) {
        launchBlock(showLoading = true) {
            newsFavoriteRepository.deleteArticleFromFavoriteList(id).collect {
                if (it != -1)
                    setState(FavoriteNewsViewState.onDeletingFavoriteResponse(true))
            }
        }
    }
}
