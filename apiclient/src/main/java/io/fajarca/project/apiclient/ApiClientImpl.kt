package io.fajarca.project.apiclient

import io.fajarca.project.base.network.exception.ClientErrorException
import io.fajarca.project.base.network.exception.EmptyResponseException
import io.fajarca.project.base.network.exception.NoInternetConnection
import io.fajarca.project.base.network.exception.ServerErrorException
import io.fajarca.project.base.network.exception.TimeoutException
import io.fajarca.project.base.network.exception.UnknownNetworkErrorException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject
import retrofit2.Response

class ApiClientImpl @Inject constructor(): ApiClient {

    override suspend fun <T> call(api: suspend () -> Response<T>): ApiResult<Exception, T> {
        return try {
            val response = api()
            when {
                response.isSuccessful && response.body() == null -> ApiResult.Failure(EmptyResponseException())
                response.isSuccessful && response.body() != null -> ApiResult.Success(response.body()!!)
                response.code() in 400..499 -> ApiResult.Failure(ClientErrorException(response.code()))
                response.code() in 500..599 -> ApiResult.Failure(ServerErrorException(response.code()))
                else -> ApiResult.Failure(UnknownNetworkErrorException(response.errorBody().toString()))
            }
        } catch (exception: Exception) {
            handleError(exception)
        }
    }

    private fun <T> handleError(exception: Exception): ApiResult.Failure<Exception, T> {
        return when (exception) {
            is UnknownHostException -> ApiResult.Failure(NoInternetConnection())
            is SocketTimeoutException -> ApiResult.Failure(TimeoutException())
            is IOException -> ApiResult.Failure(UnknownNetworkErrorException(exception.message ?: "Unknown IO Exception error"))
            else -> ApiResult.Failure(UnknownNetworkErrorException(exception.localizedMessage ?: "Unknown error"))
        }
    }

}
