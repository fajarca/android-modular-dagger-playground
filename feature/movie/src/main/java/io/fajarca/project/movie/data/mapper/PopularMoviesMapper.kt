package io.fajarca.project.movie.data.mapper

import io.fajarca.project.base.abstraction.Mapper
import io.fajarca.project.movie.data.response.GetPopularMoviesDto
import io.fajarca.project.movie.domain.entity.Movie
import javax.inject.Inject

class PopularMoviesMapper @Inject constructor() : Mapper<GetPopularMoviesDto, List<Movie>>() {

    override fun map(input: GetPopularMoviesDto): List<Movie> {
        val movies = mutableListOf<Movie>()
        for (movie in input.results.orEmpty()) {
            movies.add(Movie(movie.id ?: 0, movie.title.orEmpty(), movie.posterPath.orEmpty()))
        }
        return movies
    }

}