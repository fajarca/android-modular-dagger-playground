package io.fajarca.project.daggerplayground.main

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import retrofit2.Retrofit
import javax.inject.Inject

class MainViewModel @Inject constructor(private val preferences: SharedPreferences, private val retrofit: Retrofit) : ViewModel() {


    fun print() {

    }
}