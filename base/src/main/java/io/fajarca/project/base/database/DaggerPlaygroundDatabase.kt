package io.fajarca.project.base.database

import androidx.room.Database
import androidx.room.RoomDatabase
import io.fajarca.project.base.database.dao.MovieDao
import io.fajarca.project.base.database.entity.MovieEntity

@Database(
    entities = [
        MovieEntity::class
    ],
    version = 1
)
abstract class DaggerPlaygroundDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}