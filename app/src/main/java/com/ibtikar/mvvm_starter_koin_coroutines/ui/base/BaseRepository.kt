package com.ibtikar.mvvm_starter_koin_coroutines.ui.base

import com.ibtikar.mvvm_starter_koin_coroutines.R
import com.ibtikar.mvvm_starter_koin_coroutines.utils.coroutines.ContextProviders
import com.ibtikar.mvvm_starter_koin_coroutines.utils.erros.ApplicationException
import com.ibtikar.mvvm_starter_koin_coroutines.utils.erros.ErrorType
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.SSLException

/**
 * Created by Meslmawy on 6/10/2021
 */

abstract class BaseRepository(val contextProviders: ContextProviders) {

    private fun timeOutError(error: Throwable): Exception {
        return ApplicationException(
            throwable = error,
            type = ErrorType.Network.ResourceNotFound,
            errorMessageRes = R.string.error_time_out_connection
        )
    }

    private fun noInternetError(error: Throwable): Exception {
        return ApplicationException(
            throwable = error,
            type = ErrorType.Network.NoInternetConnection,
            errorMessageRes = R.string.error_no_internet_connection
        )
    }

    private fun unexpectedError(error: Throwable): Exception {
        return ApplicationException(
            throwable = error,
            type = ErrorType.Network.Unexpected
        )
    }

    fun <T  : Any> networkHandler(fetch: suspend () -> T) = flow {
        try {
            emit(fetch.invoke())
        } catch (throwable: Throwable) {
            handleExceptions(throwable)
        }
    }.flowOn(contextProviders.IO)

    fun <T> networkDeferredHandler(fetch: Deferred<T>) = flow {
        try {
            emit(fetch.await())
        } catch (throwable: Throwable) {
            handleExceptions(throwable)
        }
    }.flowOn(contextProviders.IO)


    private fun handleExceptions(throwable: Throwable){
        when (throwable) {
            is HttpException -> {
                handleHttpExceptions(throwable)
            }
            is TimeoutCancellationException -> {
                throw timeOutError(throwable)
            }
            is SocketTimeoutException -> {
                throw timeOutError(throwable)
            }
            is UnknownHostException -> {
                throw noInternetError(throwable)
            }
            is SSLException -> {
                throw noInternetError(throwable)
            }
            is ConnectException -> {
                throw noInternetError(throwable)
            }
            else -> {
                throw unexpectedError(throwable)
            }
        }
    }

    private fun handleHttpExceptions(throwable: HttpException) {
        when (throwable.code()) {
            400 -> {
                throw ApplicationException(
                    type = ErrorType.Network.BadRequest,
                    errorMessage = getErrorFrom(throwable)
                )
            }
            401 -> {
                throw ApplicationException(
                    type = ErrorType.Network.Unauthorized,
                    errorMessage = getErrorFrom(throwable),
                )
            }
            404 -> {
                throw ApplicationException(
                    type = ErrorType.Network.ResourceNotFound,
                    errorMessage = getErrorFrom(throwable)
                )
            }
            422 -> {
                throw ApplicationException(
                    type = ErrorType.Network.UnprocessableEntity,
                    errorMessage = getErrorFrom(throwable)
                )
            }
            else -> {
                throw ApplicationException(
                    type = ErrorType.Network.Unexpected,
                    errorMessage = getErrorFrom(throwable)
                )
            }
        }
    }

    private fun getErrorFrom(throwable: HttpException): String {
        return try {
            throwable.response()?.errorBody()?.string() ?: "Unexpected Error"
        } catch (exception: Exception) {
            "Unexpected Error"
        }
    }

}