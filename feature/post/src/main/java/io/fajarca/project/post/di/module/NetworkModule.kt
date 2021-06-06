package io.fajarca.project.post.di.module

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import io.fajarca.project.base.di.scope.ModuleScope
import io.fajarca.project.post.data.service.PostService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


@Module
class NetworkModule {
    @Provides
    @ModuleScope
    fun provideRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
            .build()
    }

    @Provides
    @ModuleScope
    fun providePostServiceApi(retrofit: Retrofit): PostService = retrofit.create(PostService::class.java)
}