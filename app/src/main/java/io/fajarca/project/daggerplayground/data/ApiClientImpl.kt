package io.fajarca.project.daggerplayground.data

import io.fajarca.project.base.Either
import io.fajarca.project.base.abstraction.ApiClient
import io.fajarca.project.base.network.exception.ClientErrorException
import io.fajarca.project.base.network.exception.EmptyResponseException
import io.fajarca.project.base.network.exception.NoInternetConnection
import io.fajarca.project.base.network.exception.ServerErrorException
import io.fajarca.project.base.network.exception.TimeoutException
import io.fajarca.project.base.network.exception.UnknownNetworkErrorException
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class ApiClientImpl @Inject constructor() : ApiClient {

    override suspend fun <T> call(api: suspend () -> Response<T>): Either<Exception, T> {
        return try {
            val response = api()
            when {
                response.isSuccessful && response.body() == null -> Either.Failure(EmptyResponseException())
                response.isSuccessful && response.body() != null -> Either.Success(response.body()!!)
                response.code() in 400..499 -> Either.Failure(ClientErrorException(response.code(), response.errorBody().toString()))
                response.code() in 500..599 -> Either.Failure(ServerErrorException(response.code()))
                else -> Either.Failure(UnknownNetworkErrorException(response.errorBody().toString()))
            }
        } catch (exception: Exception) {
            handleError(exception)
        }
    }

    private fun <T> handleError(exception: Exception): Either.Failure<Exception, T> {
        return when (exception) {
            is UnknownHostException -> Either.Failure(NoInternetConnection())
            is SocketTimeoutException -> Either.Failure(TimeoutException())
            is IOException -> Either.Failure(UnknownNetworkErrorException(exception.message ?: "Unknown IO Exception error"))
            else -> Either.Failure(UnknownNetworkErrorException(exception.localizedMessage ?: "Unknown error"))
        }
    }

}
