package io.fajarca.project.post.di.module

import dagger.Binds
import dagger.Module
import io.fajarca.project.post.data.repository.PostRepositoryImpl
import io.fajarca.project.post.domain.repository.PostRepository

@Module
interface RepositoryModule {
    @Binds
    fun bindPostRepositoryImpl(repository: PostRepositoryImpl): PostRepository
}