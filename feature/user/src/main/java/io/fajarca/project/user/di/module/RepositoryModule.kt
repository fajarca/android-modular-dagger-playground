package io.fajarca.project.user.di.module

import dagger.Binds
import dagger.Module
import io.fajarca.project.user.data.repository.UserRepositoryImpl
import io.fajarca.project.user.domain.repository.UserRepository

@Module
interface RepositoryModule {
    @Binds
    fun bindUserRepositoryImpl(repository: UserRepositoryImpl): UserRepository
}