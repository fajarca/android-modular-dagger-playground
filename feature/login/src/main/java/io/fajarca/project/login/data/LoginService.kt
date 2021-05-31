package io.fajarca.project.login.data

import retrofit2.http.GET

interface LoginService {
    @GET("users")
    suspend fun getUsers() : List<GetUsersDto>
}