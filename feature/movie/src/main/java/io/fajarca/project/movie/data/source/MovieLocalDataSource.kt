package io.fajarca.project.movie.data.source

import io.fajarca.project.persistance.DaggerPlaygroundDatabase
import io.fajarca.project.persistance.dao.MovieDao
import io.fajarca.project.persistance.entity.MovieEntity
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged


class MovieLocalDataSource @Inject constructor(
    private val database: DaggerPlaygroundDatabase
) {

    fun findAll(): Flow<List<MovieEntity>> {
        return database.movieDao().findAll().distinctUntilChanged()
    }

    suspend fun insertAll(movies : List<MovieEntity>) {
        database.movieDao().insertAll(*movies.toTypedArray())
    }

}