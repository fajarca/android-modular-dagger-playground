package io.fajarca.project.movie.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import io.fajarca.project.apiclient.di.DaggerApiClientComponent
import io.fajarca.project.base.ViewState
import io.fajarca.project.base.abstraction.BaseActivity
import io.fajarca.project.base.abstraction.BaseApplication
import io.fajarca.project.base.extension.gone
import io.fajarca.project.base.extension.visible
import io.fajarca.project.base.network.exception.ClientErrorException
import io.fajarca.project.base.network.exception.NoInternetConnection
import io.fajarca.project.base.network.exception.ServerErrorException
import io.fajarca.project.movie.databinding.ActivityMovieListBinding
import io.fajarca.project.movie.di.component.DaggerMovieComponent

class MovieListActivity : BaseActivity<ActivityMovieListBinding, MovieListViewModel>() {

    override val getViewBinding: (LayoutInflater) -> ActivityMovieListBinding
        get() = ActivityMovieListBinding::inflate


    override val getViewModelClass: Class<MovieListViewModel>
        get() = MovieListViewModel::class.java

    private val adapter by lazy { MovieRecyclerAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupRecyclerView()
        observePopularMovies()
        viewModel.getPopularMovies()
    }

    private fun setupRecyclerView() {
        val layoutManager = GridLayoutManager(this, 2)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter
        adapter.setOnMovieSelected { movie ->

        }
    }

    private fun observePopularMovies() {
        viewModel.popularMovies.observe(this) {
            when (it) {
                ViewState.Loading -> {
                    binding.progressBar.visible()
                }
                is ViewState.Success -> {
                    binding.progressBar.gone()
                    adapter.submitList(it.data)
                }
                is ViewState.Error -> {
                    binding.progressBar.gone()
                    when (val cause = it.cause) {
                        is ClientErrorException -> {
                            val code = cause.code
                        }
                        is ServerErrorException -> {
                            val code = cause.code
                        }
                        is NoInternetConnection -> {
                            Toast.makeText(this, "No connection", Toast.LENGTH_LONG).show()
                        }
                    }

                }
            }
        }
    }

    override fun setupDaggerComponent() {
        val apiClientComponent = DaggerApiClientComponent.factory().create()

        val movieComponent = DaggerMovieComponent
            .builder()
            .apiClientComponent(apiClientComponent)
            .baseComponent((application as BaseApplication).getBaseComponent())
            .build()

        movieComponent.movieListActivityComponent().create().inject(this)
    }


}