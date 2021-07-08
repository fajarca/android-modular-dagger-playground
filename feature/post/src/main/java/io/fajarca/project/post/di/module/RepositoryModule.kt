package io.fajarca.project.post.di.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.components.SingletonComponent
import io.fajarca.project.post.data.repository.PostRepositoryImpl
import io.fajarca.project.post.domain.repository.PostRepository


@Module
@InstallIn(ActivityComponent::class)
interface RepositoryModule {
    @Binds
    @ActivityScoped
    fun bindPostRepository(repository: PostRepositoryImpl): PostRepository
}