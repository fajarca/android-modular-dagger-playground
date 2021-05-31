package io.fajarca.project.daggerplayground.data

import android.content.Context
import io.fajarca.project.base.abstraction.Storage
import javax.inject.Inject


// @Inject tells Dagger how to provide instances of this type
class SharedPreferenceStorage @Inject constructor(context: Context) : Storage {

    private val sharedPreferences = context.getSharedPreferences("Dagger", Context.MODE_PRIVATE)

    override fun setString(key: String, value: String) {
        with(sharedPreferences.edit()) {
            putString(key, value)
            apply()
        }
    }

    override fun getString(key: String): String {
        return sharedPreferences.getString(key, "") ?: ""
    }
}
