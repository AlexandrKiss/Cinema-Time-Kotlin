package ua.kiss.cinematimekotlin.api

import ua.kiss.cinematimekotlin.model.Movie

interface ApiHelper {
    suspend fun getAllFilms(): List<Movie>
}