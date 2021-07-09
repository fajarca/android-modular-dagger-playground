package io.fajarca.project.user.domain.usecase

import io.fajarca.project.user.domain.repository.UserRepository
import javax.inject.Inject


class GetPinUseCase @Inject constructor(private val repository: UserRepository) {

    fun execute(): String {
        return repository.getPin()
    }

}