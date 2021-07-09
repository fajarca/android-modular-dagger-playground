package io.fajarca.project.daggerplayground.data

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import io.fajarca.project.base.abstraction.storage.Storage
import io.fajarca.project.base.dispatcher.CoroutineDispatcherProvider
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SharedPreferenceStorage @Inject constructor(
    @ApplicationContext private val context: Context,
    private val coroutineDispatcherProvider: CoroutineDispatcherProvider
) : Storage {

    private val sharedPreferences = context.getSharedPreferences("Dagger", Context.MODE_PRIVATE)

    override  fun setString(key: String, value: String) = runBlocking {
        withContext(coroutineDispatcherProvider.io) {
            with(sharedPreferences.edit()) {
                putString(key, value)
                apply()
            }
        }
    }

    override  fun getString(key: String): String = runBlocking {
         withContext(coroutineDispatcherProvider.io) {
            sharedPreferences.getString(key, null) ?: ""
        }
    }
}
