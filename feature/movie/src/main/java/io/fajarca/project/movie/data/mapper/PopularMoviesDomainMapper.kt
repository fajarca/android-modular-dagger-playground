package io.fajarca.project.movie.data.mapper

import io.fajarca.project.base.abstraction.Mapper
import io.fajarca.project.base.persistence.entity.MovieEntity
import io.fajarca.project.movie.domain.entity.Movie
import javax.inject.Inject

class PopularMoviesDomainMapper @Inject constructor() : Mapper<List<MovieEntity>, List<Movie>>() {

    override fun map(input: List<MovieEntity>): List<Movie> {
        return input.map { movie ->
            Movie(movie.id, movie.title, movie.imageUrl)
        }
    }

}