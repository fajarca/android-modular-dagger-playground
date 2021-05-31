package io.fajarca.project.login.domain.usecase

import io.fajarca.project.login.domain.entity.User
import io.fajarca.project.login.domain.repository.UserRepository
import javax.inject.Inject
import io.fajarca.project.base.Result
import io.fajarca.project.base.abstraction.UseCase

class GetUsersUseCase @Inject constructor(private val repository: UserRepository) :
    UseCase<UseCase.None, Result<List<User>>>() {

    override suspend fun execute(params: None): Result<List<User>> {
        return repository.getUsers()
    }

}