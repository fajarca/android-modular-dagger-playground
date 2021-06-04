package io.fajarca.project.base.abstraction

abstract class UseCase<I, T> {
    abstract suspend fun execute(params : I): T
    object NoParams
}
