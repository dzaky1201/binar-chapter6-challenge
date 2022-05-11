package com.dzakyhdr.moviedb.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dzakyhdr.moviedb.utils.UserDataStoreManager

class ProfileViewModelProvider (private val pref: UserDataStoreManager) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            return ProfileViewModel(pref) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}