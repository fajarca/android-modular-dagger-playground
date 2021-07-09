package io.fajarca.project.base.di.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.fajarca.project.base.dispatcher.CoroutineDispatcherProvider
import io.fajarca.project.base.dispatcher.CoroutineDispatcherProviderImpl

@Module
@InstallIn(SingletonComponent::class)
interface CoroutineDispatcherModule {
    @Binds
    fun bindDispatcher(dispatcherProviderImpl: CoroutineDispatcherProviderImpl) : CoroutineDispatcherProvider
}
