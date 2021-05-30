package io.fajarca.project.daggerplayground.login

import io.fajarca.project.daggerplayground.login.GetUserDto
import retrofit2.Call
import retrofit2.http.GET

interface LoginService {
    @GET("users")
    fun getUsers() : Call<GetUserDto>
}