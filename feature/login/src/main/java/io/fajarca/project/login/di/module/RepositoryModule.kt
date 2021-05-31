package io.fajarca.project.login.di.module

import dagger.Binds
import dagger.Module
import io.fajarca.project.login.data.repository.UserRepositoryImpl
import io.fajarca.project.login.domain.repository.UserRepository

@Module
interface RepositoryModule {
    @Binds
    fun bindUserRepositoryImpl(repository: UserRepositoryImpl): UserRepository
}