package io.fajarca.project.user.data.service

import io.fajarca.project.user.data.response.GetUsersDto
import retrofit2.Response
import retrofit2.http.GET

interface LoginService {
    @GET("users")
    suspend fun getUsers() : Response<List<GetUsersDto>>
}