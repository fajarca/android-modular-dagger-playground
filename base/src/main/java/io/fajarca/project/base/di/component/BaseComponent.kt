package io.fajarca.project.base.di.component

import com.squareup.moshi.Moshi
import dagger.Subcomponent
import io.fajarca.project.base.abstraction.ApiClient
import io.fajarca.project.base.abstraction.Storage
import io.fajarca.project.base.abstraction.dispatcher.DispatcherProvider
import io.fajarca.project.base.router.UserRouter
import okhttp3.OkHttpClient

@Subcomponent
interface BaseComponent {
    // Factory that is used to create instances of this subcomponent
    @Subcomponent.Factory
    interface Factory {
        fun create(): BaseComponent
    }

    fun storage(): Storage
    fun okHttpClient() : OkHttpClient
    fun coroutineDispatcher() : DispatcherProvider
    fun apiClient() : ApiClient
    fun moshi() : Moshi
    fun userRouter() : UserRouter
}