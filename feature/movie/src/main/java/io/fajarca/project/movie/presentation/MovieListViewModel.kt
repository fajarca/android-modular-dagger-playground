package io.fajarca.project.movie.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.fajarca.project.core.ViewState
import io.fajarca.project.core.abstraction.usecase.UseCase
import io.fajarca.project.core.dispatcher.CoroutineDispatcherProvider
import io.fajarca.project.core.extension.onError
import io.fajarca.project.core.extension.onSuccess
import io.fajarca.project.movie.domain.entity.Movie
import io.fajarca.project.movie.domain.usecase.GetPopularMoviesUseCase
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MovieListViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val coroutineDispatcherProvider: CoroutineDispatcherProvider
) : ViewModel() {

    private val _popularMovies = MutableLiveData<ViewState<List<Movie>>>()
    val popularMovies: LiveData<ViewState<List<Movie>>>
        get() = _popularMovies

    fun getPopularMovies() {
        _popularMovies.value = ViewState.Loading
        viewModelScope.launch(coroutineDispatcherProvider.io) {
            getPopularMoviesUseCase
                .execute(UseCase.NoParams)
                .onSuccess { flow -> collectMovies(flow) }
                .onError { cause -> _popularMovies.postValue(ViewState.Error(cause)) }
        }
    }

    private suspend fun collectMovies(flow: Flow<List<Movie>>) {
        flow.collect { movies ->
            _popularMovies.postValue(ViewState.Success(movies))
        }
    }
}