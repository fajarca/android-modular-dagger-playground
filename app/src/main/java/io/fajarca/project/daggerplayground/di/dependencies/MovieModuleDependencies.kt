package io.fajarca.project.daggerplayground.di.dependencies

import com.squareup.moshi.Moshi
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.fajarca.project.apiclient.ApiClient
import io.fajarca.project.core.dispatcher.CoroutineDispatcherProvider
import io.fajarca.project.persistance.DaggerPlaygroundDatabase
import okhttp3.OkHttpClient

@EntryPoint
@InstallIn(SingletonComponent::class)
interface MovieModuleDependencies {
    fun database() : DaggerPlaygroundDatabase
    fun moshi() : Moshi
    fun okHttpClient(): OkHttpClient
    fun dispatcherProvider() : CoroutineDispatcherProvider
    fun apiClient() : ApiClient
}
