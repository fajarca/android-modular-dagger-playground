package io.fajarca.project.login.presentation

import androidx.lifecycle.ViewModel
import io.fajarca.project.base.abstraction.Storage
import io.fajarca.project.login.data.LoginService
import javax.inject.Inject


class LoginViewModel @Inject constructor(private val storage: Storage, private val loginService: LoginService) : ViewModel() {

    fun getPin(): String {
       return storage.getString("pin")
    }

    fun setPin(pin : String) {
        storage.setString("pin", pin)
    }
}