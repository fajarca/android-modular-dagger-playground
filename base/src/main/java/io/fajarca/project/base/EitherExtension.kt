package io.fajarca.project.base

inline fun <T> Either<T>.unwrap(onSuccess : (T) -> Unit, onError : (exception : Exception) -> Unit) {
    when(this) {
        is Either.Success -> onSuccess(this.data)
        is Either.Error -> onError(this.cause)
    }
}