package io.fajarca.project.daggerplayground.data

import co.id.telkom.base.network.HttpResult
import io.fajarca.project.base.Result
import io.fajarca.project.base.abstraction.ApiClient
import io.fajarca.project.base.abstraction.dispatcher.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject


class ApiClientImpl @Inject constructor() : ApiClient {
    override suspend fun <T> call(api: suspend () -> T): Result<T> {
        return try {
            Result.Success(api.invoke())
        } catch (throwable: Throwable) {
            handleError(throwable)
        }
    }

    private fun handleError(throwable: Throwable): Result.Error {
        return when (throwable) {
            is HttpException -> {
                return when (throwable.code()) {
                    in 400..451 -> Result.Error(
                        HttpResult.CLIENT_ERROR,
                        throwable.code(),
                        throwable.message(),
                        null
                    )
                    in 500..599 -> Result.Error(
                        HttpResult.SERVER_ERROR,
                        throwable.code(),
                        "Server error",
                        null
                    )
                    else -> Result.Error(
                        HttpResult.NOT_DEFINED,
                        throwable.code(),
                        "Undefined error",
                        null
                    )
                }
            }
            is UnknownHostException -> Result.Error(
                HttpResult.NO_CONNECTION,
                null,
                "No internet connection",
                null
            )
            is SocketTimeoutException -> Result.Error(
                HttpResult.TIMEOUT,
                null,
                "Slow connection",
                null
            )
            is IOException -> Result.Error(
                HttpResult.BAD_RESPONSE,
                null,
                throwable.message,
                null
            )
            else -> Result.Error(HttpResult.NOT_DEFINED, null, throwable.message, null)
        }
    }

}
