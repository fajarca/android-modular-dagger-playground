package io.fajarca.project.daggerplayground.di.module

import dagger.Module
import dagger.Provides
import io.fajarca.project.daggerplayground.login.data.LoginService
import retrofit2.Retrofit

@Module
class ApiServiceModule {
    @Provides

    fun provideLoginServiceApi(retrofit: Retrofit) = retrofit.create(LoginService::class.java)
}