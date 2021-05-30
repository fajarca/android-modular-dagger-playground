package io.fajarca.project.daggerplayground.login.di

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import retrofit2.Retrofit
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val preferences: SharedPreferences, private val retrofit: Retrofit) : ViewModel() {

    fun getPin(): String {
       return preferences.getString("pin", "Pin is not set") ?: ""
    }
}