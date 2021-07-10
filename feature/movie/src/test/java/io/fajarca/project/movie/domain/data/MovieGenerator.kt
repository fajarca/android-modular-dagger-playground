package io.fajarca.project.movie.domain.data

import io.fajarca.project.movie.domain.entity.Movie

object MovieGenerator {

    fun generateMovie(): Movie {
        return Movie(1, "Sample movie", "Some image url")
    }

    fun generateMovies(count : Int): List<Movie> {
        val movies = mutableListOf<Movie>()
        repeat(count) {
            movies.add(Movie(it, "Sampe movie $it", "Some image url $it"))
        }
        return movies
    }
}