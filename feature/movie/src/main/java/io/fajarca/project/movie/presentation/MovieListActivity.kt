package io.fajarca.project.movie.presentation

import android.os.Bundle
import android.view.LayoutInflater
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.EntryPointAccessors
import io.fajarca.project.core.ViewState
import io.fajarca.project.core.abstraction.activity.DynamicFeatureActivity
import io.fajarca.project.core.extension.gone
import io.fajarca.project.core.extension.visible
import io.fajarca.project.daggerplayground.di.dependencies.MovieModuleDependencies
import io.fajarca.project.movie.databinding.ActivityMovieListBinding
import io.fajarca.project.movie.di.component.DaggerMovieComponent

class MovieListActivity : DynamicFeatureActivity<ActivityMovieListBinding, MovieListViewModel>() {

    private val adapter by lazy { MovieRecyclerAdapter() }

    override val getViewBinding: (LayoutInflater) -> ActivityMovieListBinding
        get() = ActivityMovieListBinding::inflate

    override val getViewModelClass: Class<MovieListViewModel>
        get() = MovieListViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupRecyclerView()
        setupToolbar()
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
                }
            }
        }
    }

    override fun setupDaggerComponent() {
        DaggerMovieComponent
            .builder()
            .moduleDependencies(
                EntryPointAccessors.fromApplication(
                    applicationContext,
                    MovieModuleDependencies::class.java
                )
            )
            .build()
            .inject(this)
    }
}