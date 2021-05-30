package io.fajarca.project.daggerplayground.login.presentation

import androidx.lifecycle.ViewModel
import io.fajarca.project.daggerplayground.login.data.LoginService
import io.fajarca.project.daggerplayground.data.SharedPreferenceStorage
import io.fajarca.project.daggerplayground.di.scope.ActivityScope
import javax.inject.Inject


class LoginViewModel @Inject constructor(private val storage: SharedPreferenceStorage, private val loginService: LoginService) : ViewModel() {

    fun getPin(): String {
       return storage.getString("pin")
    }
}