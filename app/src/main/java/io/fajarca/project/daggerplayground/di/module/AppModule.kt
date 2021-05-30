package io.fajarca.project.daggerplayground.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import io.fajarca.project.daggerplayground.DaggerPlaygroundApp
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideContext(app: Application): Context = app


    @Provides
    @Singleton
    fun providesPreference(context: Context): SharedPreferences = context.getSharedPreferences("file", Context.MODE_PRIVATE)


    @Provides
    @Singleton
    fun providesSharedPreference(sharedPreferences: SharedPreferences): SharedPreferences.Editor = sharedPreferences.edit()

}