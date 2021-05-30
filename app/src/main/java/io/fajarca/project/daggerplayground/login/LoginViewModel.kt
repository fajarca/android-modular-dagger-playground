package io.fajarca.project.daggerplayground.login

import androidx.lifecycle.ViewModel
import io.fajarca.project.daggerplayground.data.SharedPreferenceStorage
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val storage: SharedPreferenceStorage, private val loginService: LoginService) : ViewModel() {

    fun getPin(): String {
       return storage.getString("pin")
    }
}