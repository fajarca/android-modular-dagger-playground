package io.fajarca.project.core.di.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.fajarca.project.core.dispatcher.CoroutineDispatcherProvider
import io.fajarca.project.core.dispatcher.CoroutineDispatcherProviderImpl

@Module
@InstallIn(SingletonComponent::class)
interface CoroutineDispatcherModule {
    @Binds
    fun bindDispatcher(dispatcherProviderImpl: CoroutineDispatcherProviderImpl) : CoroutineDispatcherProvider
}
