package io.fajarca.project.daggerplayground.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.fajarca.project.daggerplayground.MainActivity

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract fun contributesHomeActivity(): MainActivity


}