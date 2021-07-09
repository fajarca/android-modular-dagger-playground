package io.fajarca.project.base.abstraction.usecase

abstract class UseCase<I, T> {
    abstract suspend fun execute(params : I): T
    object NoParams
}
