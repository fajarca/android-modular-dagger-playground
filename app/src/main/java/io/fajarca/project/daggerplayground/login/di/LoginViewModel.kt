package io.fajarca.project.daggerplayground.login.di

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val preferences: SharedPreferences) : ViewModel() {

    fun getPin(): String {
       return preferences.getString("pin", "Pin is not set") ?: ""
    }
}