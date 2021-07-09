package io.fajarca.project.movie.domain.usecase

import io.fajarca.project.apiclient.exception.ServerErrorException
import io.fajarca.project.core.Either
import io.fajarca.project.core.abstraction.usecase.UseCase
import io.fajarca.project.movie.domain.entity.Movie
import io.fajarca.project.movie.domain.repository.MovieRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test

class GetPopularMoviesUseCaseTest {

    private val repository: MovieRepository = mockk()
    private val useCase = GetPopularMoviesUseCase(repository)

    @Test
    fun shouldGetMoviesFromRepositoryWhenInvoked() = runBlocking {
        //Given
        val movies: Flow<List<Movie>> = flowOf(emptyList())
        coEvery { repository.getPopularMovies() } returns Either.Success(movies)

        //when
        useCase.execute(UseCase.NoParams)

        //Then
        coVerify { repository.getPopularMovies() }
    }

    @Test
    fun shouldReturnMoviesWhenGetMoviesSuccess() = runBlocking {
        //Given
        val expected: Either.Success<Exception, Flow<List<Movie>>> =
            Either.Success(flowOf(emptyList()))
        coEvery { repository.getPopularMovies() } returns expected

        //when
        val actual = useCase.execute(UseCase.NoParams)

        //Then
        assertEquals(expected, actual)
    }

    @Test
    fun shouldReturnExceptionWhenGetMoviesError() = runBlocking {
        //Given
        val expected: Either.Failure<Exception, Flow<List<Movie>>> =
            Either.Failure(ServerErrorException(500))
        coEvery { repository.getPopularMovies() } returns expected

        //when
        val actual = useCase.execute(UseCase.NoParams)

        //Then
        assertEquals(expected, actual)
    }
}