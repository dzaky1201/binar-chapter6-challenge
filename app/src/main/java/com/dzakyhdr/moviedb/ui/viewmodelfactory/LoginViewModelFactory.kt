package com.dzakyhdr.moviedb.ui.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dzakyhdr.moviedb.data.local.auth.UserRepository
import com.dzakyhdr.moviedb.ui.login.LoginViewModel
import com.dzakyhdr.moviedb.utils.UserDataStoreManager
import javax.inject.Inject


class LoginViewModelFactory (
    private val userRepository: UserRepository,
    private val pref: UserDataStoreManager
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(userRepository, pref) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}