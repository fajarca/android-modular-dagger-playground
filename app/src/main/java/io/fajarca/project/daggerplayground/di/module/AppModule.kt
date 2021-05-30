package io.fajarca.project.daggerplayground.di.module

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import io.fajarca.project.daggerplayground.MyPertaminaApp
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideContext(app: MyPertaminaApp): Context = app


    @Provides
    @Singleton
    fun providesPreference(app: MyPertaminaApp): SharedPreferences = app.getSharedPreferences("file", Context.MODE_PRIVATE)


    @Provides
    @Singleton
    fun providesSharedPreference(sharedPreferences: SharedPreferences): SharedPreferences.Editor = sharedPreferences.edit()

}