package io.fajarca.project.apiclient

import io.fajarca.project.apiclient.exception.ClientErrorException
import io.fajarca.project.apiclient.exception.NoInternetConnection
import io.fajarca.project.apiclient.exception.ServerErrorException
import io.fajarca.project.apiclient.exception.TimeoutException
import io.fajarca.project.apiclient.exception.UnknownNetworkErrorException
import io.fajarca.project.apiclient.response.ApiResponse
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject
import javax.inject.Singleton
import retrofit2.HttpException

@Singleton
internal class ApiClientImpl @Inject constructor() : ApiClient {

    override suspend fun <T> call(apiCall: suspend () -> T): ApiResponse<Exception, T> {
        return try {
            ApiResponse.Success(apiCall())
        } catch (exception: Exception) {
            handleError(exception)
        }
    }

    private fun handleError(exception: Exception): ApiResponse<Exception, Nothing> {
        return when (exception) {
            is HttpException -> {
                 when (exception.code()) {
                     in 400..499 -> ApiResponse.Failure(ClientErrorException(exception.code()))
                     in 500..599 -> ApiResponse.Failure(ServerErrorException(exception.code()))
                     else -> ApiResponse.Failure(UnknownNetworkErrorException(exception.message ?: "ew"))
                }
            }
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
