package io.fajarca.project.core

sealed class Either<out L, out R> {
    data class Left<out L>(val cause: L) : Either<L, Nothing>()
    data class Right<out R>(val data: R) : Either<Nothing, R>()

    val isLeft get() = this is Left<L>
    val isRight get() = this is Right<R>

    fun <L> left(cause: L) = Left(cause)
    fun <R> right(data: R) = Right(data)
}