package ua.kiss.cinematimekotlin.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ua.kiss.cinematimekotlin.api.ApiHelper
import ua.kiss.cinematimekotlin.model.Movie
import ua.kiss.cinematimekotlin.utils.Resource

class MoviesListViewModel(private val apiHelper: ApiHelper) : ViewModel() {

    private val users = MutableLiveData<Resource<List<Movie>>>()

    init {
        fetchFilms()
    }

    private fun fetchFilms() {
        viewModelScope.launch {
            users.postValue(Resource.loading(null))
            try {
                val usersFromApi = apiHelper.getAllFilms()
                users.postValue(Resource.success(usersFromApi))
            } catch (e: Exception) {
                users.postValue(Resource.error(e.toString(), null))
            }
        }
    }

    fun getAllFilms(): LiveData<Resource<List<Movie>>> {
        return users
    }
}