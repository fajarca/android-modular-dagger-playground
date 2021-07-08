package io.fajarca.project.daggerplayground

import android.app.Application
import android.content.Context
import com.google.android.play.core.splitcompat.SplitCompat
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.components.SingletonComponent
import io.fajarca.project.persistance.dao.MovieDao
import okhttp3.OkHttpClient


@HiltAndroidApp
class DaggerPlaygroundApp : Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        SplitCompat.install(this)
    }

}
