package io.fajarca.project.user.data.repository

import io.fajarca.project.base.di.scope.ModuleScope
import io.fajarca.project.user.data.service.UserService
import io.fajarca.project.user.data.mapper.UserMapper
import io.fajarca.project.user.domain.entity.User
import javax.inject.Inject
import io.fajarca.project.base.Either
import io.fajarca.project.base.abstraction.ApiClient
import io.fajarca.project.user.domain.repository.UserRepository
import java.lang.Exception

@ModuleScope
class UserRepositoryImpl @Inject constructor(
    private val mapper: UserMapper,
    private val apiClient : ApiClient,
    private val userService: UserService
) : UserRepository {

    override suspend fun getUsers(): Either<Exception, List<User>> {
        val apiResult = apiClient.call { userService.getUsers() }
        return when (apiResult) {
            is Either.Failure -> Either.Failure(apiResult.cause)
            is Either.Success -> Either.Success(mapper.map(apiResult.data))
        }
    }

}