package io.fajarca.project.base.extension

import io.fajarca.project.base.Either

inline fun  <A, B> Either<A, B>.unwrap(onError : (A) -> Unit, onSuccess : (B) -> Unit) {
    when(this) {
        is Either.Failure -> onError(this.cause)
        is Either.Success -> onSuccess(this.data)
    }
}

inline fun <A, B> Either<A, B>.onError(onError : (A) -> Unit): Either<A,B> {
    if (this is Either.Failure) {
        onError(this.cause)
    }
    return this
}

inline fun <A,B> Either<A,B>.onSuccess(onSuccess : (B) -> Unit): Either<A, B> {
    if (this is Either.Success) {
        onSuccess(this.data)
    }
    return this
}

