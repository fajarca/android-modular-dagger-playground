package io.fajarca.project.apiclient

import io.fajarca.project.apiclient.exception.ClientErrorException
import io.fajarca.project.apiclient.exception.NoInternetConnection
import io.fajarca.project.apiclient.exception.ServerErrorException
import io.fajarca.project.apiclient.exception.TimeoutException
import io.fajarca.project.apiclient.exception.UnknownNetworkErrorException
import io.fajarca.project.apiclient.response.ApiResponse
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
        val clientErrorResponseCode = 400..499
        val serverErrorResponsecode = 500..599
        return when (exception) {

            is HttpException -> {
                when (exception.code()) {
                    in clientErrorResponseCode -> ApiResponse.Failure(ClientErrorException(exception.code()))
                    in serverErrorResponsecode -> ApiResponse.Failure(ServerErrorException(exception.code()))
                    else -> ApiResponse.Failure(
                        UnknownNetworkErrorException("Response code ${exception.code()} is invalid")
                    )
                }
            }
            is UnknownHostException -> ApiResponse.Failure(NoInternetConnection())
            is SocketTimeoutException -> ApiResponse.Failure(TimeoutException())
            else -> ApiResponse.Failure(UnknownNetworkErrorException("Undefined error"))
        }
    }

}
