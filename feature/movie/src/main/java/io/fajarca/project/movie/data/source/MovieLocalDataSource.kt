package io.fajarca.project.movie.data.source

import io.fajarca.project.persistance.dao.MovieDao
import io.fajarca.project.persistance.entity.MovieEntity
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged


class MovieLocalDataSource @Inject constructor(
    private val movieDao: MovieDao
) {

    fun findAll(): Flow<List<MovieEntity>> {
        return movieDao.findAll().distinctUntilChanged()
    }

    suspend fun insertAll(movies : List<MovieEntity>) {
        movies.forEach { movie ->
            movieDao.insert(movie)
        }
    }

}