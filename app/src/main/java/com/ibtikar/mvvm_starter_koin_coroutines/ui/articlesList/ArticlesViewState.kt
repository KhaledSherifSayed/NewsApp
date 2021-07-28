package com.ibtikar.mvvm_starter_koin_coroutines.ui.articlesList

import com.ibtikar.mvvm_starter_koin_coroutines.data.models.ArticleModelResponse
import com.ibtikar.mvvm_starter_koin_coroutines.ui.base.ViewState

/**
 * Created by Meslmawy on 6/10/2021
 */


sealed class ArticlesViewState : ViewState() {
    data class onArticlesResponse(val data: List<ArticleModelResponse>?) : Loaded<List<ArticleModelResponse>?>(data)
}
