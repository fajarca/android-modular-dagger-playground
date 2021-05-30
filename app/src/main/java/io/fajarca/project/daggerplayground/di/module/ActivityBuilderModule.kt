package io.fajarca.project.daggerplayground.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.fajarca.project.daggerplayground.login.LoginActivity

@Module
abstract class ActivityBuilderModule {
    @ContributesAndroidInjector
    abstract fun contributesLoginActivity(): LoginActivity
}