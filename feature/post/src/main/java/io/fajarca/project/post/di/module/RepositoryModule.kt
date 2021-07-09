package io.fajarca.project.post.di.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.components.SingletonComponent
import io.fajarca.project.post.data.repository.PostRepositoryImpl
import io.fajarca.project.post.domain.repository.PostRepository


@Module
@InstallIn(ViewModelComponent::class)
interface RepositoryModule {
    @Binds
    fun bindPostRepository(repository: PostRepositoryImpl): PostRepository
}