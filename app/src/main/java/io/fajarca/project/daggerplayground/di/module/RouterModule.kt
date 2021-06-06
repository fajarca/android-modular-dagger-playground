package io.fajarca.project.daggerplayground.di.module

import android.util.SparseArray
import dagger.Module
import dagger.Provides
import io.fajarca.project.base.router.Routable
import io.fajarca.project.post.PostRouter
import io.fajarca.project.user.presentation.main.UserRouter

@Module
class RouterModule {

    @Provides
    fun provideRouterData(): SparseArray<Routable<*, *>>  {
        return SparseArray<Routable<*, *>>().apply {
            put(PostRouter.deepLinkCode, PostRouter)
            put(UserRouter.deepLinkCode, UserRouter)
        }
    }
}