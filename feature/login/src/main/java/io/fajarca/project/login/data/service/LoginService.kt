package io.fajarca.project.login.data.service

import io.fajarca.project.login.data.response.GetUsersDto
import retrofit2.http.GET

interface LoginService {
    @GET("users")
    suspend fun getUsers() : List<GetUsersDto>
}