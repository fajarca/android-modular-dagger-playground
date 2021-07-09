package io.fajarca.project.daggerplayground.di.module

import android.util.SparseArray
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.fajarca.project.core.router.Routable
import io.fajarca.project.post.presentation.list.PostRouter
import io.fajarca.project.user.presentation.list.UserRouter

@Module
@InstallIn(SingletonComponent::class)
object RouterModule {

    @Provides
    fun provideRouterData(): SparseArray<Routable<*, *>>  {
        return SparseArray<Routable<*, *>>().apply {
            put(PostRouter.deepLinkCode, PostRouter)
            put(UserRouter.deepLinkCode, UserRouter)
        }
    }
}