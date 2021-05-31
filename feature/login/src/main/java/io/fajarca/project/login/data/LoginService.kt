package io.fajarca.project.login.data

import retrofit2.Call
import retrofit2.http.GET

interface LoginService {
    @GET("users")
    fun getUsers() : Call<GetUserDto>
}