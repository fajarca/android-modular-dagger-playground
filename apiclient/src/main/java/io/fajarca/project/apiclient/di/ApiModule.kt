package io.fajarca.project.apiclient.di

import dagger.Binds
import dagger.Module
import io.fajarca.project.apiclient.ApiClient
import io.fajarca.project.apiclient.ApiClientImpl

@Module
abstract class ApiModule {

    @Binds
    internal abstract fun provideApi(apiClientImpl: ApiClientImpl) : ApiClient

}