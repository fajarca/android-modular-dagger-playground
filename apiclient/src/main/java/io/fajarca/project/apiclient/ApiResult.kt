package io.fajarca.project.apiclient

sealed class ApiResult<A,B> {
    class Failure<A,B>(val cause : A) : ApiResult<A, B>()
    class Success<A,B>(val data : B) : ApiResult<A, B>()
}
