package com.ibtikar.mvvm_starter_koin_coroutines.ui.newsList

import com.ibtikar.mvvm_starter_koin_coroutines.data.models.NewsModelResponse
import com.ibtikar.mvvm_starter_koin_coroutines.ui.base.ViewState

/**
 * Created by Meslmawy on 6/10/2021
 */


sealed class NewsViewState : ViewState() {
    data class onNewsResponse(val data: List<NewsModelResponse>?) : Loaded<List<NewsModelResponse>?>(data)
    data class onAddingFavoriteResponse(val data: Boolean?) : Loaded<Boolean?>(data)
}
