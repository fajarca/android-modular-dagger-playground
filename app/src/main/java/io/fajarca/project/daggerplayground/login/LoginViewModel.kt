package io.fajarca.project.daggerplayground.login

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val preferences: SharedPreferences, private val loginService: LoginService) : ViewModel() {

    fun getPin(): String {
       return preferences.getString("pin", "Pin is not set") ?: ""
    }
}