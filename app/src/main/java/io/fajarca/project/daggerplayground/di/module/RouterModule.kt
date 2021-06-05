package io.fajarca.project.daggerplayground.di.module

import dagger.Binds
import dagger.Module
import io.fajarca.project.daggerplayground.presentation.UserRouterImpl
import io.fajarca.project.base.router.UserRouter

@Module
abstract class RouterModule {
    @Binds
    abstract fun provideUserRouter(userRouter : UserRouterImpl) : UserRouter
}