package io.fajarca.project.base.abstraction

import io.fajarca.project.base.Result

interface ApiClient {
    suspend fun <T> call(api: suspend () -> T): Result<T>
}