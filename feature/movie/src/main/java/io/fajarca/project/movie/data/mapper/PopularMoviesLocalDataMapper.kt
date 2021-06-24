package io.fajarca.project.movie.data.mapper

import io.fajarca.project.base.abstraction.Mapper
import io.fajarca.project.base.database.entity.MovieEntity
import io.fajarca.project.movie.domain.entity.Movie
import javax.inject.Inject

class PopularMoviesLocalDataMapper @Inject constructor() : Mapper<List<Movie>, List<MovieEntity>>() {

    override fun map(input: List<Movie>): List<MovieEntity> {
        val movies = mutableListOf<MovieEntity>()
        for (movie in input) {
            movies.add(MovieEntity(movie.id, movie.title, movie.imageUrl, "popular"))
        }
        return movies
    }

}