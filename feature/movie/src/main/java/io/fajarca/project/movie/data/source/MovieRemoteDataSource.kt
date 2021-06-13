package io.fajarca.project.movie.data.source

import io.fajarca.project.apiclient.ApiClient
import io.fajarca.project.apiclient.ApiResponse
import io.fajarca.project.base.Either
import io.fajarca.project.movie.data.response.GetPopularMoviesDto
import io.fajarca.project.movie.data.service.MovieService
import io.fajarca.project.movie.domain.entity.Movie
import javax.inject.Inject

class MovieRemoteDataSource @Inject constructor(
    private val apiClient: ApiClient,
    private val movieService: MovieService
) {

    suspend fun getPopularMovies(): Either<Exception, GetPopularMoviesDto> {
        val response = apiClient.call { movieService.getPopularMovies() }
        return when(response) {
            is ApiResponse.Success -> Either.Success(response.data)
            is ApiResponse.Failure -> Either.Failure(response.cause)
        }
    }


}