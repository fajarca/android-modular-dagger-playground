package io.fajarca.project.daggerplayground.di.module

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import io.fajarca.project.daggerplayground.BuildConfig
import io.fajarca.project.daggerplayground.di.annotation.ViewModelKey
import io.fajarca.project.daggerplayground.login.LoginService
import io.fajarca.project.daggerplayground.login.LoginViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    internal abstract fun providesLoginViewModel(viewModel: LoginViewModel): ViewModel

}
