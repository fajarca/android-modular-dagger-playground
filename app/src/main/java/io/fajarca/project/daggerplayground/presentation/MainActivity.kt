package io.fajarca.project.daggerplayground.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest
import com.google.android.play.core.splitinstall.SplitInstallStateUpdatedListener
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus
import io.fajarca.project.base.router.Router
import io.fajarca.project.daggerplayground.DaggerPlaygroundApp
import io.fajarca.project.daggerplayground.databinding.ActivityMainBinding
import io.fajarca.project.common.route.PostRouterData
import io.fajarca.project.common.route.UserRouterData
import io.fajarca.project.daggerplayground.BuildConfig
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val splitInstallManager by lazy { SplitInstallManagerFactory.create(this) }

    @Inject
    lateinit var router: Router

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as DaggerPlaygroundApp).appComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupView()
    }

    private fun setupView() {
        binding.btnGoToUserModule.setOnClickListener {
            router.routeToActivity(this, UserRouterData(4))
        }
        binding.btnGoToPostModule.setOnClickListener {
            router.routeToActivity(this, PostRouterData(4))
        }
        binding.btnGoToMovieModule.setOnClickListener {
            downloadMovieModule()
        }
    }

    private val installListener = SplitInstallStateUpdatedListener { state ->
        val size = state.totalBytesToDownload()
        when (state.status()) {
            SplitInstallSessionStatus.CANCELED -> {
            }
            SplitInstallSessionStatus.CANCELING -> {

            }
            SplitInstallSessionStatus.DOWNLOADED -> {
                Toast.makeText(this, "Downloaded", Toast.LENGTH_LONG).show()
            }
            SplitInstallSessionStatus.DOWNLOADING -> {
                Toast.makeText(this, "Downloading movie module", Toast.LENGTH_LONG).show()
            }
            SplitInstallSessionStatus.FAILED -> {

            }
            SplitInstallSessionStatus.INSTALLED -> {
                Toast.makeText(this, "Movie module downloaded", Toast.LENGTH_LONG).show()
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
            .addOnSuccessListener { sessionId -> goToMovieScreen() }
            .addOnFailureListener { exception -> }
    }

    private fun goToMovieScreen() {
        if (splitInstallManager.installedModules.contains("movie")) {
            val intent = Intent()
            intent.setClassName(BuildConfig.APPLICATION_ID, "io.fajarca.project.movie.MovieListActivity")
            startActivity(intent)
        } else {
            Log.e("", "Registration feature is not installed")
        }
    }

}