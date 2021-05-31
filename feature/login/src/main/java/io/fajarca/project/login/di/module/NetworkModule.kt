package io.fajarca.project.login.di.module

import dagger.Module
import dagger.Provides
import io.fajarca.project.base.ModuleScope
import io.fajarca.project.login.data.service.LoginService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


@Module
class NetworkModule {
    @Provides
    @ModuleScope
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @ModuleScope
    fun provideLoginServiceApi(retrofit: Retrofit) = retrofit.create(LoginService::class.java)
}