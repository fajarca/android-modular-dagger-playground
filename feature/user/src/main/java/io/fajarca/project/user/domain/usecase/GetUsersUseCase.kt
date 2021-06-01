package io.fajarca.project.user.domain.usecase

import io.fajarca.project.user.domain.entity.User
import io.fajarca.project.user.domain.repository.UserRepository
import javax.inject.Inject
import io.fajarca.project.base.Either
import io.fajarca.project.base.abstraction.UseCase

class GetUsersUseCase @Inject constructor(private val repository: UserRepository) :
    UseCase<UseCase.NoParams, Either<List<User>>>() {

    override suspend fun execute(params: NoParams): Either<List<User>> {
        return repository.getUsers()
    }

}