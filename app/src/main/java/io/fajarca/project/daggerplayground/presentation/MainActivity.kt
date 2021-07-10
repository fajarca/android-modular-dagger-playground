package io.fajarca.project.daggerplayground.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest
import com.google.android.play.core.splitinstall.SplitInstallStateUpdatedListener
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus
import dagger.hilt.android.AndroidEntryPoint
import io.fajarca.project.core.router.Router
import io.fajarca.project.navigation.PostRouterData
import io.fajarca.project.navigation.UserRouterData
import io.fajarca.project.daggerplayground.BuildConfig
import io.fajarca.project.daggerplayground.databinding.ActivityMainBinding
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val splitInstallManager by lazy { SplitInstallManagerFactory.create(this) }
    private var binding: ActivityMainBinding? = null

    @Inject
    lateinit var router: Router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setupView()
        setupToolbar()
    }

    private fun setupView() {
        binding?.btnGoToUserModule?.setOnClickListener {
            router.routeToActivity(this, UserRouterData(4))
        }
        binding?.btnGoToPostModule?.setOnClickListener {
            router.routeToActivity(this, PostRouterData(4))
        }
        binding?.btnGoToMovieModule?.setOnClickListener {
            downloadMovieModule()
        }
    }

    private fun setupToolbar() {
        val toolbarBinding = binding?.toolbar?.toolbar
        setSupportActionBar(toolbarBinding)
    }

    private val installListener = SplitInstallStateUpdatedListener { state ->
        when (state.status()) {
            SplitInstallSessionStatus.CANCELED -> {
            }
            SplitInstallSessionStatus.CANCELING -> {

            }
            SplitInstallSessionStatus.DOWNLOADED -> {
                Snackbar.make(
                    binding?.root ?: return@SplitInstallStateUpdatedListener,
                    "Downloaded",
                    Snackbar.LENGTH_LONG
                ).show()
            }
            SplitInstallSessionStatus.DOWNLOADING -> {
                val downloadSize = state.totalBytesToDownload()
                val progress = state.bytesDownloaded()
                Log.v("MainActivity", "Downloading dynamic feature.. $progress of $downloadSize")

                Snackbar.make(
                    binding?.root ?: return@SplitInstallStateUpdatedListener,
                    "Downloading movie module",
                    Snackbar.LENGTH_LONG
                ).show()
            }
            SplitInstallSessionStatus.FAILED -> {

            }
            SplitInstallSessionStatus.INSTALLED -> {
                Snackbar.make(
                    binding?.root ?: return@SplitInstallStateUpdatedListener,
                    "Movie module installed",
                    Snackbar.LENGTH_LONG
                ).show()
            }
            SplitInstallSessionStatus.INSTALLING -> {

            }
            SplitInstallSessionStatus.PENDING -> {

            }
            SplitInstallSessionStatus.REQUIRES_USER_CONFIRMATION -> {

            }
            SplitInstallSessionStatus.UNKNOWN -> {

            }
        }
    }

    private fun downloadMovieModule() {
        val splitInstallRequest = SplitInstallRequest.newBuilder()
            .addModule("movie")
            .build()

        splitInstallManager.registerListener(installListener)

        splitInstallManager.startInstall(splitInstallRequest)
            .addOnSuccessListener { goToMovieScreen() }
            .addOnFailureListener { }
    }

    private fun goToMovieScreen() {
        if (splitInstallManager.installedModules.contains("movie")) {
            val intent = Intent()
            intent.setClassName(
                BuildConfig.APPLICATION_ID,
                "io.fajarca.project.movie.presentation.MovieListActivity"
            )
            startActivity(intent)
        } else {
            Log.e("", "Registration feature is not installed")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}