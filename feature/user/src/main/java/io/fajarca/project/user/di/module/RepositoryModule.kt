package io.fajarca.project.user.di.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import io.fajarca.project.user.data.repository.UserRepositoryImpl
import io.fajarca.project.user.domain.repository.UserRepository

@Module
@InstallIn(ViewModelComponent::class)
interface RepositoryModule {
    @Binds
    fun bindUserRepositoryImpl(repository: UserRepositoryImpl): UserRepository
}