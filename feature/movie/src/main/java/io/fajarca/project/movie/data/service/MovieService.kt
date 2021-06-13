package io.fajarca.project.movie.data.service

import io.fajarca.project.movie.data.response.GetPopularMoviesDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieService {
    @GET("movie/popular")
    suspend fun getPopularMovies() : Response<GetPopularMoviesDto>
}