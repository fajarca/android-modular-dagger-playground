package io.fajarca.project.movie.data.repository

import io.fajarca.project.base.Either
import io.fajarca.project.movie.data.mapper.PopularMoviesLocalDataMapper
import io.fajarca.project.movie.data.mapper.PopularMoviesMapper
import io.fajarca.project.movie.data.source.MovieLocalDataSource
import io.fajarca.project.movie.data.source.MovieRemoteDataSource
import io.fajarca.project.movie.domain.entity.Movie
import io.fajarca.project.movie.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val moviesMapper: PopularMoviesMapper,
    private val moviesLocalDataMapper: PopularMoviesLocalDataMapper,
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val movieLocalDataSource: MovieLocalDataSource
) : MovieRepository {


    override suspend fun getPopularMovies(): Either<Exception, List<Movie>> {
        val apiResult = movieRemoteDataSource.getPopularMovies()
        return when (apiResult) {
            is Either.Failure -> Either.Failure(apiResult.cause)
            is Either.Success -> {
                val movies =  moviesMapper.map(apiResult.data)
                movieLocalDataSource.insertAll(moviesLocalDataMapper.map(movies))
                Either.Success(movies)
            }
        }
    }


}