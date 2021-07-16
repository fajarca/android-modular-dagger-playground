package io.fajarca.project.post.di.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import io.fajarca.project.post.data.repository.PostRepositoryImpl
import io.fajarca.project.post.domain.repository.PostRepository


@Module
@InstallIn(ViewModelComponent::class)
interface RepositoryModule {
    @Binds
    fun bindPostRepository(repository: PostRepositoryImpl): PostRepository
}