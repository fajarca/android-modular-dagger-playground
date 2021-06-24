package io.fajarca.project.persistance.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import io.fajarca.project.persistance.DaggerPlaygroundDatabase
import io.fajarca.project.persistance.dao.MovieDao
import javax.inject.Singleton

@Module
class PersistenceModule {

    @Provides
    fun provideDatabase(context: Context): DaggerPlaygroundDatabase {
        return Room.databaseBuilder(
            context,
            DaggerPlaygroundDatabase::class.java,
            "daggerplayground"
        ).build()
    }

}