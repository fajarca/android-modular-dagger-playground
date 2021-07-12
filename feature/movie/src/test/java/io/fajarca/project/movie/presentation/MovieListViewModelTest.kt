package io.fajarca.project.movie.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import io.fajarca.project.core.Either
import io.fajarca.project.core.ViewState
import io.fajarca.project.core.abstraction.usecase.UseCase
import io.fajarca.project.movie.domain.data.MovieGenerator
import io.fajarca.project.movie.domain.entity.Movie
import io.fajarca.project.movie.domain.helper.CoroutineTestRule
import io.fajarca.project.movie.domain.helper.LifeCycleTestOwner
import io.fajarca.project.movie.domain.usecase.GetPopularMoviesUseCase
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MovieListViewModelTest {

    @get:Rule
    val instantTestExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private lateinit var lifecycleOwner: LifeCycleTestOwner

    private val popularMoviesObserver: Observer<ViewState<List<Movie>>> = spyk()

    private val getPopularMoviesUseCase: GetPopularMoviesUseCase = mockk()

    private val viewModel by lazy {
        MovieListViewModel(
            getPopularMoviesUseCase,
            coroutineTestRule.dispatcherProvider
        )
    }

    @Before
    fun setUp() {
        lifecycleOwner = LifeCycleTestOwner()
        lifecycleOwner.onCreate()

        viewModel.popularMovies.observe(lifecycleOwner, popularMoviesObserver)
    }

    @After
    fun tearDown() {
        lifecycleOwner.onDestroy()
    }

    @Test
    fun getPopularMovies_whenFetchSuccess_shouldPostSuccessToObserver() = runBlocking {
            //Given
            lifecycleOwner.onResume()

            val expected = Either.Right(flowOf(MovieGenerator.generateMovies(2)))

            coEvery { getPopularMoviesUseCase.execute(UseCase.NoParams) } returns expected

            //When
            viewModel.getPopularMovies()

            val movies = mutableListOf<Movie>()
            expected.data.collect {
                for (movie in it) {
                    movies.add(Movie(movie.id, movie.title, movie.imageUrl))
                }
            }

            //Then
            verify {
                popularMoviesObserver.onChanged(ViewState.Loading)
                popularMoviesObserver.onChanged(ViewState.Success(movies))
            }

        }
}