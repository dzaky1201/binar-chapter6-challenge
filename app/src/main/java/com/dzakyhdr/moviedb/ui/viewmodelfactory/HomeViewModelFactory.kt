package com.dzakyhdr.moviedb.ui.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dzakyhdr.moviedb.data.local.auth.UserRepository
import com.dzakyhdr.moviedb.data.remote.MovieRepository
import com.dzakyhdr.moviedb.ui.home.HomeViewModel
import com.dzakyhdr.moviedb.utils.UserDataStoreManager
import javax.inject.Inject

class HomeViewModelFactory (
    private val movieRepository: MovieRepository,
    private val userRepository: UserRepository,
    private val pref: UserDataStoreManager
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(movieRepository, userRepository, pref) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}