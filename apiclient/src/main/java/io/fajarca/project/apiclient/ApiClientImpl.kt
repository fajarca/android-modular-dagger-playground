package io.fajarca.project.apiclient

import io.fajarca.project.apiclient.exception.ClientErrorException
import io.fajarca.project.apiclient.exception.EmptyResponseException
import io.fajarca.project.apiclient.exception.NoInternetConnection
import io.fajarca.project.apiclient.exception.ServerErrorException
import io.fajarca.project.apiclient.exception.TimeoutException
import io.fajarca.project.apiclient.exception.UnknownNetworkErrorException
import io.fajarca.project.apiclient.response.ApiResponse
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject
import retrofit2.Response

internal class ApiClientImpl @Inject constructor() : ApiClient {

    override suspend fun <T> call(apiCall: suspend () -> Response<T>): ApiResponse<Exception, T> {
        return try {
            val response = apiCall()
            when {
                response.isSuccessful && response.body() == null -> ApiResponse.Failure(
                    EmptyResponseException()
                )
                response.isSuccessful && response.body() != null -> ApiResponse.Success(response.body()!!)
                response.code() in 400..499 -> ApiResponse.Failure(ClientErrorException(response.code()))
                response.code() in 500..599 -> ApiResponse.Failure(ServerErrorException(response.code()))
                else -> ApiResponse.Failure(
                    UnknownNetworkErrorException(
                        response.errorBody().toString()
                    )
                )
            }
        } catch (exception: Exception) {
            handleError(exception)
        }
    }

    private fun <T> handleError(exception: Exception): ApiResponse.Failure<Exception, T> {
        return when (exception) {
            is UnknownHostException -> ApiResponse.Failure(NoInternetConnection())
            is SocketTimeoutException -> ApiResponse.Failure(TimeoutException())
            is IOException -> ApiResponse.Failure(
                UnknownNetworkErrorException(
                    exception.message ?: "Unknown IO Exception error"
                )
            )
            else -> ApiResponse.Failure(
                UnknownNetworkErrorException(
                    exception.localizedMessage ?: "Unknown error"
                )
            )
        }
    }

}
