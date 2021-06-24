package io.fajarca.project.movie.domain.usecase

import io.fajarca.project.movie.domain.entity.Movie
import io.fajarca.project.movie.domain.repository.MovieRepository
import javax.inject.Inject
import io.fajarca.project.base.Either
import io.fajarca.project.base.abstraction.UseCase
import io.fajarca.project.base.di.scope.FeatureScope
import kotlinx.coroutines.flow.Flow

@FeatureScope
class GetPopularMoviesUseCase @Inject constructor(private val repository: MovieRepository) :
    UseCase<UseCase.NoParams, Either<Exception, Flow<List<Movie>>>>() {

    override suspend fun execute(params: NoParams): Either<Exception, Flow<List<Movie>>> {
        return repository.getPopularMovies()
    }

}