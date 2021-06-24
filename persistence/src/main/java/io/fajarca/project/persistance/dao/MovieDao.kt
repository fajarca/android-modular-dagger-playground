package io.fajarca.project.persistance.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.fajarca.project.persistance.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class MovieDao {
    @Query("SELECT * FROM movies")
    abstract fun findAll(): Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertAll(vararg movie: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(movie: MovieEntity)

    @Delete
    abstract suspend fun delete(movie: MovieEntity)
}