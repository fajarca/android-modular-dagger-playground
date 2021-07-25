package io.fajarca.project.apiclient

import io.fajarca.project.apiclient.response.ApiResponse


interface ApiClient {
    suspend fun <T> call(apiCall: suspend () -> T): ApiResponse<Exception, T>
}