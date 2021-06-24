package io.fajarca.project.base.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import io.fajarca.project.base.persistence.dao.MovieDao
import io.fajarca.project.base.persistence.entity.MovieEntity

@Database(
    entities = [
        MovieEntity::class
    ],
    version = 1
)
abstract class DaggerPlaygroundDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}