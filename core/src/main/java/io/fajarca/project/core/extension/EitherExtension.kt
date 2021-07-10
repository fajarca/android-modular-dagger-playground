package io.fajarca.project.core.extension

import io.fajarca.project.core.Either

inline fun  <A, B> Either<A, B>.fold(onError : (A) -> Unit, onSuccess : (B) -> Unit) {
    when(this) {
        is Either.Left -> onError(this.cause)
        is Either.Right -> onSuccess(this.data)
    }
}

inline fun <A, B> Either<A, B>.onError(onError : (A) -> Unit): Either<A,B> {
    if (this is Either.Left) {
        onError(this.cause)
    }
    return this
}

inline fun <A,B> Either<A,B>.onSuccess(onSuccess : (B) -> Unit): Either<A, B> {
    if (this is Either.Right) {
        onSuccess(this.data)
    }
    return this
}


fun <L, R> Either<L, R>.getOrElse(value: R): R =
    when (this) {
        is Either.Left -> value
        is Either.Right -> data
    }

fun <L, R> Either<L, R>.getLeftOrElse(value: L): L =
    when (this) {
        is Either.Left -> cause
        is Either.Right -> value
    }


