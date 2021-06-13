package io.fajarca.project.movie.domain.repository

import io.fajarca.project.base.Either
import io.fajarca.project.movie.domain.entity.Movie

interface MovieRepository {
    suspend fun getPopularMovies(): Either<Exception, List<Movie>>
}