package io.fajarca.project.daggerplayground.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.fajarca.project.daggerplayground.di.login.LoginModule
import io.fajarca.project.daggerplayground.di.login.LoginViewModelModule
import io.fajarca.project.daggerplayground.login.LoginActivity

@Module
abstract class ActivityBuilderModule {


    @ContributesAndroidInjector(modules = [LoginModule::class, LoginViewModelModule::class])
    abstract fun contributesLoginActivity(): LoginActivity

}