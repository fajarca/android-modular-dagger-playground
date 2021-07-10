package io.fajarca.project.movie.domain.usecase

import io.fajarca.project.apiclient.exception.ServerErrorException
import io.fajarca.project.core.Either
import io.fajarca.project.core.abstraction.usecase.UseCase
import io.fajarca.project.core.extension.getLeftOrElse
import io.fajarca.project.movie.domain.data.MovieGenerator
import io.fajarca.project.movie.domain.repository.MovieRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
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
        val movies = MovieGenerator.generateMovies(2)
        val expected = Either.Right(flowOf(movies))

        coEvery { repository.getPopularMovies() } returns expected

        //when
        useCase.execute(UseCase.NoParams)

        //Then
        coVerify { repository.getPopularMovies() }
    }

    @Test
    fun shouldReturnMoviesWhenGetMoviesSuccess() = runBlocking {
        //Given
        val movies = MovieGenerator.generateMovies(2)
        val expected = Either.Right(flowOf(movies))
        coEvery { repository.getPopularMovies() } returns expected

        //when
        val actual = useCase.execute(UseCase.NoParams)

        //Then
        assertEquals(expected, actual)
    }

    @Test
    fun shouldReturnExceptionWhenGetMoviesError() = runBlocking {
        //Given
        val error = ServerErrorException(500)
        val expected = Either.Left(error)
        coEvery { repository.getPopularMovies() } returns expected

        //when
        val actual = useCase.execute(UseCase.NoParams)

        //Then
        assert(actual is Either.Left)
        assertEquals(error, actual.getLeftOrElse(null))
    }
}