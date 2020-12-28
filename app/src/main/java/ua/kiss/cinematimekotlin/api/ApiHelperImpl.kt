package ua.kiss.cinematimekotlin.api

import ua.kiss.cinematimekotlin.model.Movie

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {
    override suspend fun getAllFilms() = apiService.getAllFilms()

}