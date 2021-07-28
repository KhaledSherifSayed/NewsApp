package com.ibtikar.mvvm_starter_koin_coroutines.ui.base


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ibtikar.mvvm_starter_koin_coroutines.utils.coroutines.ContextProviders
import com.ibtikar.mvvm_starter_koin_coroutines.utils.erros.ApplicationException
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * Created by Meslmawy on 6/10/2021
 */

abstract class BaseViewModel(private val contextProvider: ContextProviders) : ViewModel() {

    private val internalState = MutableStateFlow<ViewState>(ViewState.Initial)
    val state: StateFlow<ViewState> = internalState

    protected fun setState(state: ViewState) {
        internalState.value = state
    }

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        setState(ViewState.Error(throwable as ApplicationException))
    }

    fun launchBlock(showLoading: Boolean = true,block: suspend CoroutineScope.() -> Unit) {
        setState(ViewState.Loading(showLoading))
        viewModelScope.launch(contextProvider.Main + coroutineExceptionHandler) {
            block.invoke(this)
        }
    }

    fun asyncBlock(showLoading: Boolean = true,block: suspend CoroutineScope.() -> Unit) {
        setState(ViewState.Loading(showLoading))
        viewModelScope.async(contextProvider.Main + coroutineExceptionHandler) {
            block.invoke(this)
        }
    }
}