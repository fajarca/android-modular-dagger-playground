package io.fajarca.project.login.data.repository

import io.fajarca.project.base.ModuleScope
import io.fajarca.project.base.network.ApiClientManager
import io.fajarca.project.login.data.LoginService
import io.fajarca.project.login.data.mapper.UserMapper
import io.fajarca.project.login.domain.entity.User
import javax.inject.Inject
import io.fajarca.project.base.Result
import io.fajarca.project.login.domain.repository.UserRepository

@ModuleScope
class UserRepositoryImpl @Inject constructor(
    private val mapper: UserMapper,
    private val apiClientManager: ApiClientManager,
    private val loginService: LoginService
) : UserRepository {

    override suspend fun getUsers(): Result<List<User>> {
        val apiResult = apiClientManager.call { loginService.getUsers() }
        return when (apiResult) {
            is Result.Success -> Result.Success(mapper.map(apiResult.data))
            else -> Result.Error()
        }
    }

}