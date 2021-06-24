package io.fajarca.project.persistance

import androidx.room.Database
import androidx.room.RoomDatabase
import io.fajarca.project.persistance.dao.MovieDao
import io.fajarca.project.persistance.entity.MovieEntity

@Database(
    entities = [
        MovieEntity::class
    ],
    version = 1
)
abstract class DaggerPlaygroundDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}