package io.fajarca.project.daggerplayground.di.module

import dagger.Binds
import dagger.Module
import io.fajarca.project.base.abstraction.dispatcher.DispatcherProvider
import io.fajarca.project.daggerplayground.data.DispatcherProviderImpl

@Module
interface CoroutineDispatcherModule {
    @Binds
    fun bindDispatcher(dispatcherProviderImpl: DispatcherProviderImpl) : DispatcherProvider
}
