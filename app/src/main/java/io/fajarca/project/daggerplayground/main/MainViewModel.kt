package io.fajarca.project.daggerplayground.main

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor(private val preferences: SharedPreferences) : ViewModel() {


}