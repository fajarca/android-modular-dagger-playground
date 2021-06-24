package io.fajarca.project.movie.data.source

import io.fajarca.project.base.database.dao.MovieDao
import io.fajarca.project.base.database.entity.MovieEntity
import javax.inject.Inject


class MovieLocalDataSource @Inject constructor(
    private val movieDao: MovieDao
) {

    suspend fun findAll(): List<MovieEntity> {
        return movieDao.findAll()
    }

    suspend fun insertAll(movies : List<MovieEntity>) {
        movies.forEach { movie ->
            movieDao.insert(movie)
        }
    }

}