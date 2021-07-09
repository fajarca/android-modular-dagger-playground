package io.fajarca.project.apiclient

import io.fajarca.project.apiclient.response.ApiResponse
import retrofit2.Response


interface ApiClient {
    suspend fun <T> call(api: suspend () -> Response<T>): ApiResponse<Exception, T>
}