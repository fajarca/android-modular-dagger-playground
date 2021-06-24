package io.fajarca.project.base.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.fajarca.project.base.database.entity.MovieEntity

@Dao
abstract class MovieDao {
    @Query("SELECT * FROM movies")
    abstract suspend fun findAll(): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertAll(vararg movie: MovieEntity)

    @Insert
    abstract suspend fun insert(movie: MovieEntity)

    @Delete
    abstract suspend fun delete(movie: MovieEntity)
}