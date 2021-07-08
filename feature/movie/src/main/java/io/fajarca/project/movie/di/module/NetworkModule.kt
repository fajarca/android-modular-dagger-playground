package io.fajarca.project.movie.di.module

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import io.fajarca.project.movie.BuildConfig
import io.fajarca.project.movie.data.service.MovieService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(ActivityComponent::class)
object NetworkModule {

    @Provides
    fun provideAuthInterceptor(): Interceptor {
        return Interceptor { chain ->
            val newRequest = chain
                .request()
                .newBuilder()
                .addHeader(
                    "Authorization",
                    "Bearer " + BuildConfig.MOVIE_DB_API_KEY
                )
                .build()

            chain.proceed(newRequest)
        }
    }

    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        authInterceptor: Interceptor,
        moshi: Moshi
    ): Retrofit {
        val authOkHttpClient = okHttpClient
            .newBuilder()
            .addInterceptor(authInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(authOkHttpClient)
            .build()
    }


    @Provides
    fun provideMovieServiceApi(retrofit: Retrofit): MovieService =
        retrofit.create(MovieService::class.java)
}