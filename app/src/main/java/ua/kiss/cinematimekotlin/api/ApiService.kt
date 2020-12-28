package ua.kiss.cinematimekotlin.api

import retrofit2.http.GET
import ua.kiss.cinematimekotlin.model.Movie

interface ApiService {
    @GET("movie/all")
    suspend fun getAllFilms(): List<Movie>
}