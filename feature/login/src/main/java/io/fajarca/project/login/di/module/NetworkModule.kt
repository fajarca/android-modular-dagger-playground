package io.fajarca.project.login.di.module

import dagger.Module
import dagger.Provides
import io.fajarca.project.login.data.LoginService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


@Module
class NetworkModule {
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun provideLoginServiceApi(retrofit: Retrofit) = retrofit.create(LoginService::class.java)
}