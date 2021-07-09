package io.fajarca.project.core.abstraction.usecase

abstract class UseCase<I, T> {
    abstract suspend fun execute(params : I): T
    object NoParams
}
