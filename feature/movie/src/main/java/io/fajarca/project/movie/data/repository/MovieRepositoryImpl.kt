package io.fajarca.project.movie.data.repository

import io.fajarca.project.base.Either
import io.fajarca.project.movie.data.mapper.PopularMoviesDataMapper
import io.fajarca.project.movie.data.mapper.PopularMoviesDomainMapper
import io.fajarca.project.movie.data.source.MovieLocalDataSource
import io.fajarca.project.movie.data.source.MovieRemoteDataSource
import io.fajarca.project.movie.domain.entity.Movie
import io.fajarca.project.movie.domain.repository.MovieRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepositoryImpl @Inject constructor(
    private val moviesDomainMapper: PopularMoviesDomainMapper,
    private val moviesDataMapper: PopularMoviesDataMapper,
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val movieLocalDataSource: MovieLocalDataSource
) : MovieRepository {


    override suspend fun getPopularMovies(): Either<Exception, Flow<List<Movie>>> {
        val apiResult = movieRemoteDataSource.getPopularMovies()
        return when (apiResult) {
            is Either.Failure -> {
                val movies = movieLocalDataSource.findAll().map { moviesDomainMapper.map(it) }
                Either.Success(movies)
            }
            is Either.Success -> {
                movieLocalDataSource.insertAll(moviesDataMapper.map(apiResult.data))
                val movies = movieLocalDataSource.findAll().map { moviesDomainMapper.map(it) }
                Either.Success(movies)
            }
        }
    }


}