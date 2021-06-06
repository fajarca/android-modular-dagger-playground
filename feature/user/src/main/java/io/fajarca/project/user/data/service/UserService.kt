package io.fajarca.project.user.data.service

import io.fajarca.project.user.data.response.GetUserDetailDto
import io.fajarca.project.user.data.response.GetUsersDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface UserService {
    @GET("users")
    suspend fun getUsers() : Response<List<GetUsersDto>>

    @GET("users/{userId}")
    suspend fun getUserDetail(@Path("userId") userId : Int) : Response<GetUserDetailDto>
}