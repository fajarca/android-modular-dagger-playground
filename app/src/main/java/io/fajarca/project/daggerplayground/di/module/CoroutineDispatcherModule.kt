package io.fajarca.project.daggerplayground.di.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.fajarca.project.base.abstraction.dispatcher.DispatcherProvider
import io.fajarca.project.daggerplayground.data.DispatcherProviderImpl

@Module
@InstallIn(SingletonComponent::class)
interface CoroutineDispatcherModule {
    @Binds
    fun bindDispatcher(dispatcherProviderImpl: DispatcherProviderImpl) : DispatcherProvider
}
