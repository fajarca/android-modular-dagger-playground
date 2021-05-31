package io.fajarca.project.base.abstraction

abstract class UseCase<I, out T> {
    abstract suspend fun execute(params : I): T
    object None
}
