package io.fajarca.project.post.di

import android.util.SparseArray
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.fajarca.project.core.router.Routable
import io.fajarca.project.post.presentation.list.PostRouter

@Module
@InstallIn(SingletonComponent::class)
object FakeRouterModule {

    @Provides
    fun provideRouterData(): SparseArray<Routable<*, *>>  {
        return SparseArray<Routable<*, *>>().apply {
            put(PostRouter.deepLinkCode, PostRouter)
        }
    }
}