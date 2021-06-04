package io.fajarca.project.user.data.source

import io.fajarca.project.base.Either
import io.fajarca.project.base.abstraction.ApiClient
import io.fajarca.project.user.data.response.GetUsersDto
import io.fajarca.project.user.data.service.UserService
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(
    private val apiClient: ApiClient,
    private val userService: UserService
) {

    suspend fun getUsers(): Either<Exception, List<GetUsersDto>> {
        return apiClient.call { userService.getUsers() }
    }

}