package io.fajarca.project.base.abstraction

import io.fajarca.project.base.Either
import retrofit2.Response

interface ApiClient {
    suspend fun <T> call(api: suspend () -> Response<T>): Either<T>
}