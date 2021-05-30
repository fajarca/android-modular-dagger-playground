package io.fajarca.project.daggerplayground.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.fajarca.project.daggerplayground.login.di.LoginActivity
import io.fajarca.project.daggerplayground.login.di.LoginViewModelModule
import io.fajarca.project.daggerplayground.main.MainActivity

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract fun contributesHomeActivity(): MainActivity

    @ContributesAndroidInjector(modules = [LoginViewModelModule::class])
    abstract fun contributesLoginActivity(): LoginActivity

}