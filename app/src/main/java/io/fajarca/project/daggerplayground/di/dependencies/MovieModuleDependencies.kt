package io.fajarca.project.daggerplayground.di.dependencies

import com.squareup.moshi.Moshi
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.fajarca.project.apiclient.ApiClient
import io.fajarca.project.base.abstraction.dispatcher.DispatcherProvider
import io.fajarca.project.persistance.DaggerPlaygroundDatabase
import io.fajarca.project.persistance.dao.MovieDao
import okhttp3.OkHttpClient

@EntryPoint
@InstallIn(SingletonComponent::class)
interface MovieModuleDependencies {
    fun database() : DaggerPlaygroundDatabase
    fun movieDao(): MovieDao
    fun moshi() : Moshi
    fun okHttpClient(): OkHttpClient
    fun dispatcherProvider() : DispatcherProvider
    fun apiClient() : ApiClient
}
