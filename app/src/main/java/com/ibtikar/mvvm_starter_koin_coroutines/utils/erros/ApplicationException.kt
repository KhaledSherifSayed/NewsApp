package com.ibtikar.mvvm_starter_koin_coroutines.utils.erros

import java.lang.RuntimeException

/**
 * Created by Meslmawy on 2/8/2021
 */


data class ApplicationException(
    val type: ErrorType,
    val errorMessage: String? = null,
    val errorMessageRes: Int? = null,
    val throwable: Throwable? = null
) :  RuntimeException()

sealed class ErrorType {

    sealed class Network : ErrorType() {
        object BadRequest : Network()
        object Unauthorized : Network()
        object ResourceNotFound : Network()
        object UnprocessableEntity : Network()
        object Unexpected : Network()
        object NoInternetConnection : Network()
    }
    object none : ErrorType()
    object Warning : ErrorType()
    object Unexpected : ErrorType()
    object UserError : ErrorType()
}
