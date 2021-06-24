package io.fajarca.project.daggerplayground.di.module

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import io.fajarca.project.base.database.DaggerPlaygroundDatabase
import io.fajarca.project.base.database.dao.MovieDao
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context): DaggerPlaygroundDatabase {
        return Room.databaseBuilder(
            context,
            DaggerPlaygroundDatabase::class.java,
            "daggerplayground"
        ).build()
    }

    @Provides
    fun provideMovieDao(database: DaggerPlaygroundDatabase): MovieDao {
        return database.movieDao()
    }


}