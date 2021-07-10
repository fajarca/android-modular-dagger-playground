package io.fajarca.project.user.data.source

import io.fajarca.project.apiclient.ApiClient
import io.fajarca.project.apiclient.response.ApiResponse
import io.fajarca.project.core.Either
import io.fajarca.project.user.data.response.GetUserDetailDto
import io.fajarca.project.user.data.response.GetUsersDto
import io.fajarca.project.user.data.service.UserService
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(
    private val apiClient: ApiClient,
    private val userService: UserService
) {

    suspend fun getUsers(): Either<Exception, List<GetUsersDto>> {
        val response = apiClient.call { userService.getUsers() }
        return when(response) {
            is ApiResponse.Success -> Either.Right(response.data)
            is ApiResponse.Failure -> Either.Left(response.cause)
        }
    }

    suspend fun getUserDetail(userId : Int): Either<Exception, GetUserDetailDto> {
        val response = apiClient.call { userService.getUserDetail(userId) }
        return when(response) {
            is ApiResponse.Success -> Either.Right(response.data)
            is ApiResponse.Failure -> Either.Left(response.cause)
        }
    }

}