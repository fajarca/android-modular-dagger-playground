package io.fajarca.project.base

sealed class Either<out T> {
    data class Success<out T>(val data: T) : Either<T>()
    data class Error(val cause: Exception) : Either<Nothing>()
}
