package io.fajarca.project.apiclient.response

sealed class ApiResponse<out L,out R> {
    data class Failure<out L,out R>(val cause : L) : ApiResponse<L, R>()
    data class Success<out L,out R>(val data : R) : ApiResponse<L, R>()
}
