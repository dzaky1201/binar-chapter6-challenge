package com.dzakyhdr.moviedb.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dzakyhdr.moviedb.data.remote.MovieRepository
import com.dzakyhdr.moviedb.utils.UserDataStoreManager

class HomeViewModelFactory(
    private val repository: MovieRepository,
    private val pref: UserDataStoreManager
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository, pref) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}