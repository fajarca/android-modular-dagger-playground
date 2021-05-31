package io.fajarca.project.daggerplayground.data

import android.content.Context
import io.fajarca.project.base.abstraction.Storage
import io.fajarca.project.base.abstraction.dispatcher.DispatcherProvider
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SharedPreferenceStorage @Inject constructor(
    private val context: Context,
    private val dispatcherProvider: DispatcherProvider
) : Storage {

    private val sharedPreferences = context.getSharedPreferences("Dagger", Context.MODE_PRIVATE)

    override  fun setString(key: String, value: String) = runBlocking {
        withContext(dispatcherProvider.io) {
            with(sharedPreferences.edit()) {
                putString(key, value)
                apply()
            }
        }
    }

    override  fun getString(key: String): String = runBlocking {
         withContext(dispatcherProvider.io) {
            sharedPreferences.getString(key, "") ?: ""
        }
    }
}
