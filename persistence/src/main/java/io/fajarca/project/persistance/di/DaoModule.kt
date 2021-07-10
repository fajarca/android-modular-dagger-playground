package io.fajarca.project.persistance.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import io.fajarca.project.persistance.DaggerPlaygroundDatabase
import io.fajarca.project.persistance.dao.MovieDao

@Module
@InstallIn(ActivityComponent::class)
object DaoModule {

    @Provides
    fun provideMovieDao(database: DaggerPlaygroundDatabase): MovieDao {
        return database.movieDao()
    }

}