package io.fajarca.project.movie.data.repository

import io.fajarca.project.base.Either
import io.fajarca.project.movie.data.mapper.PopularMoviesMapper
import io.fajarca.project.movie.data.source.MovieRemoteDataSource
import io.fajarca.project.movie.domain.entity.Movie
import io.fajarca.project.movie.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val moviesMapper: PopularMoviesMapper,
    private val movieRemoteDataSource: MovieRemoteDataSource
) : MovieRepository {


    override suspend fun getPopularMovies(): Either<Exception, List<Movie>> {
        val apiResult = movieRemoteDataSource.getPopularMovies()
        return when (apiResult) {
            is Either.Failure -> Either.Failure(apiResult.cause)
            is Either.Success -> Either.Success(moviesMapper.map(apiResult.data))
        }
    }


}