package ua.kiss.cinematimekotlin.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ua.kiss.cinematimekotlin.api.ApiHelper
import ua.kiss.cinematimekotlin.ui.MoviesListViewModel

class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MoviesListViewModel::class.java)) {
            return MoviesListViewModel(apiHelper) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}