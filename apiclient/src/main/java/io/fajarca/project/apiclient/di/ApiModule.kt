package io.fajarca.project.apiclient.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.fajarca.project.apiclient.ApiClient
import io.fajarca.project.apiclient.ApiClientImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class ApiModule {

    @Binds
    internal abstract fun provideApi(apiClientImpl: ApiClientImpl) : ApiClient

}