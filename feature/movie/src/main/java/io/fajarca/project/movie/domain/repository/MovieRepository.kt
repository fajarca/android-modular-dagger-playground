package io.fajarca.project.movie.domain.repository

import io.fajarca.project.core.Either
import io.fajarca.project.movie.domain.entity.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getPopularMovies(): Either<Exception, Flow<List<Movie>>>
}