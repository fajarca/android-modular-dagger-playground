package io.fajarca.project.movie.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.EntryPointAccessors
import io.fajarca.project.apiclient.exception.ClientErrorException
import io.fajarca.project.apiclient.exception.NoInternetConnection
import io.fajarca.project.apiclient.exception.ServerErrorException
import io.fajarca.project.base.ViewState
import io.fajarca.project.base.extension.gone
import io.fajarca.project.base.extension.visible
import io.fajarca.project.daggerplayground.di.module.MovieModuleDependencies
import io.fajarca.project.movie.databinding.ActivityMovieListBinding
import io.fajarca.project.movie.di.component.DaggerMovieComponent
import javax.inject.Inject

class MovieListActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val adapter by lazy { MovieRecyclerAdapter() }
    private var binding: ActivityMovieListBinding? = null
    private lateinit var viewModel: MovieListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerMovieComponent
            .builder()
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    applicationContext,
                    MovieModuleDependencies::class.java
                )
            )
            .build()
            .inject(this)


        binding = ActivityMovieListBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MovieListViewModel::class.java)

        setupRecyclerView()
        // setupToolbar()
        observePopularMovies()
        viewModel.getPopularMovies()
    }

    private fun setupRecyclerView() {
        val layoutManager = GridLayoutManager(this, 2)
        binding?.recyclerView?.layoutManager = layoutManager
        binding?.recyclerView?.adapter = adapter
        adapter.setOnMovieSelected { movie ->

        }
    }

    private fun observePopularMovies() {
        viewModel.popularMovies.observe(this) {
            when (it) {
                ViewState.Loading -> {
                    binding?.progressBar?.visible()
                }
                is ViewState.Success -> {
                    binding?.progressBar?.gone()
                    adapter.submitList(it.data)
                }
                is ViewState.Error -> {
                    binding?.progressBar?.gone()
                    when (val cause = it.cause) {
                        is ClientErrorException -> {
                            val code = cause.code
                        }
                        is ServerErrorException -> {
                            val code = cause.code
                        }
                        is NoInternetConnection -> {
                            Snackbar.make(
                                binding?.root ?: return@observe,
                                "No connection",
                                Snackbar.LENGTH_LONG
                            ).show()
                        }
                    }

                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}