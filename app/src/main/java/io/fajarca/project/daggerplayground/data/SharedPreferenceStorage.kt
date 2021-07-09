package io.fajarca.project.daggerplayground.data

import android.content.Context
import android.util.Log
import dagger.hilt.android.qualifiers.ApplicationContext
import io.fajarca.project.core.abstraction.storage.Storage
import io.fajarca.project.core.dispatcher.CoroutineDispatcherProvider
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


@Singleton
class SharedPreferenceStorage @Inject constructor(
    @ApplicationContext private val context: Context,
    private val coroutineDispatcherProvider: CoroutineDispatcherProvider
) : Storage, CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + coroutineDispatcherProvider.io

    private val sharedPreferences = context.getSharedPreferences("Dagger", Context.MODE_PRIVATE)

    override fun setString(key: String, value: String) {
        launch(coroutineDispatcherProvider.io) {
            with(sharedPreferences.edit()) {
                putString(key, value)
                apply()
            }
        }
    }

    override fun getString(key: String): String = runBlocking {
        async(coroutineDispatcherProvider.io) {
            sharedPreferences.getString(key, null) ?: ""
        }.await()

    }

}
