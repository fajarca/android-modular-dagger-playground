package io.fajarca.project.daggerplayground.di.module

import dagger.Binds
import dagger.Module
import io.fajarca.project.base.abstraction.ApiClient
import io.fajarca.project.daggerplayground.data.ApiClientImpl

@Module
abstract class ApiModule {
    @Binds
    abstract fun providesApiClient(apiClient : ApiClientImpl) : ApiClient
}
