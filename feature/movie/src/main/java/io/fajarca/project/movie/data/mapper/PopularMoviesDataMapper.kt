package io.fajarca.project.movie.data.mapper

import io.fajarca.project.base.abstraction.Mapper
import io.fajarca.project.base.persistence.entity.MovieEntity
import io.fajarca.project.movie.data.response.GetPopularMoviesDto
import javax.inject.Inject

class PopularMoviesDataMapper @Inject constructor() :
    Mapper<GetPopularMoviesDto, List<MovieEntity>>() {

    override fun map(input: GetPopularMoviesDto): List<MovieEntity> {
        val movies = mutableListOf<MovieEntity>()
        for (movie in input.results.orEmpty()) {
            movies.add(
                MovieEntity(
                    movie.id ?: 0,
                    movie.title.orEmpty(),
                    movie.posterPath.orEmpty(),
                    "popular"
                )
            )
        }
        return movies
    }

}