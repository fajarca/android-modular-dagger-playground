package io.fajarca.project.movie.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.fajarca.project.base.ViewState
import io.fajarca.project.base.abstraction.UseCase
import io.fajarca.project.base.abstraction.dispatcher.DispatcherProvider
import io.fajarca.project.base.extension.onError
import io.fajarca.project.base.extension.onSuccess
import io.fajarca.project.movie.domain.entity.Movie
import io.fajarca.project.movie.domain.usecase.GetPopularMoviesUseCase
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MovieListViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {

    private val _popularMovies = MutableLiveData<ViewState<List<Movie>>>()
    val popularMovies: LiveData<ViewState<List<Movie>>>
        get() = _popularMovies

    fun getPopularMovies() {
        _popularMovies.value = ViewState.Loading
        viewModelScope.launch(dispatcherProvider.io) {
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