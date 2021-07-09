package io.fajarca.project.user.domain.usecase

import io.fajarca.project.core.abstraction.usecase.UseCase
import io.fajarca.project.user.domain.repository.UserRepository
import javax.inject.Inject


class SetPinUseCase @Inject constructor(private val repository: UserRepository) :
    UseCase<String, Unit>() {

    override suspend fun execute(params: String) {
        return repository.setPin(params)
    }

}