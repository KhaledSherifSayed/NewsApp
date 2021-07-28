package com.ibtikar.mvvm_starter_koin_coroutines.ui.favorite

import com.ibtikar.mvvm_starter_koin_coroutines.data.models.NewsModelResponse
import com.ibtikar.mvvm_starter_koin_coroutines.ui.base.ViewState

/**
 * Created by Meslmawy on 6/10/2021
 */


sealed class FavoriteNewsViewState : ViewState() {
    data class onNewsResponse(val data: List<NewsModelResponse>?) : Loaded<List<NewsModelResponse>?>(data)
    data class onDeletingFavoriteResponse(val data: Boolean?) : Loaded<Boolean?>(data)
}
