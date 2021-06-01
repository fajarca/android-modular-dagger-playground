package io.fajarca.project.base.extension

import io.fajarca.project.base.Either

inline fun <T> Either<T>.unwrap(onSuccess : (T) -> Unit, onError : (exception : Exception) -> Unit) {
    when(this) {
        is Either.Success -> onSuccess(this.data)
        is Either.Error -> onError(this.cause)
    }
}

inline fun <T> Either<T>.onSuccess(onSuccess : (T) -> Unit): Either<T> {
    if (this is Either.Success) {
        onSuccess(this.data)
    }
    return this
}

inline fun <T> Either<T>.onError(onError : (exception : Exception) -> Unit): Either<T> {
    if (this is Either.Error) {
        onError(this.cause)
    }
    return this
}