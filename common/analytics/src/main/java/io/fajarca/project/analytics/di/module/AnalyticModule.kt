package io.fajarca.project.analytics.di.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import io.fajarca.project.analytics.Analytic
import io.fajarca.project.analytics.AnalyticImpl

@Module
@InstallIn(ActivityComponent::class)
interface AnalyticModule {
    @Binds
    fun bindAnalytic(analytic: AnalyticImpl) : Analytic
}