package io.fajarca.project.daggerplayground.di.module

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import io.fajarca.project.persistance.dao.MovieDao
import okhttp3.OkHttpClient

@EntryPoint
@InstallIn(ActivityComponent::class)
interface MovieModuleDependencies {
    fun movieDao(): MovieDao
    fun okHttpClient(): OkHttpClient
}
