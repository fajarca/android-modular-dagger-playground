package io.fajarca.project.user.domain.usecase

import io.fajarca.project.core.Either
import io.fajarca.project.core.abstraction.usecase.UseCase
import io.fajarca.project.user.domain.entity.User
import io.fajarca.project.user.domain.repository.UserRepository
import javax.inject.Inject


class GetUsersUseCase @Inject constructor(private val repository: UserRepository) :
    UseCase<UseCase.NoParams, Either<Exception, List<User>>>() {

    override suspend fun execute(params: NoParams): Either<Exception, List<User>> {
        return repository.getUsers()
    }

}