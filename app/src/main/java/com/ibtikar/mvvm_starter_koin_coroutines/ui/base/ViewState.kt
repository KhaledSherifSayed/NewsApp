package com.ibtikar.mvvm_starter_koin_coroutines.ui.base

import com.ibtikar.mvvm_starter_koin_coroutines.utils.erros.ApplicationException

/**
 * Created by Meslmawy on 6/10/2021
 */

abstract class ViewState {
    object Initial : ViewState()
    data class Loading(val loading: Boolean) : ViewState()
    data class Error(val exception: ApplicationException) : ViewState()
    abstract class Loaded<out T>(val r: T) : ViewState()
    object Empty : ViewState()
}