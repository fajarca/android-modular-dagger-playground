package io.fajarca.project.movie.data.service

import io.fajarca.project.movie.data.response.GetPopularMoviesDto
import retrofit2.http.GET

interface MovieService {
    @GET("movie/popular")
    suspend fun getPopularMovies() : GetPopularMoviesDto
}