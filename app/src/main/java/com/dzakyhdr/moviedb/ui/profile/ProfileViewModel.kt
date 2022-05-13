package com.dzakyhdr.moviedb.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.dzakyhdr.moviedb.utils.UserDataStoreManager
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val pref: UserDataStoreManager,
) : ViewModel() {

    fun getIdUsername(): LiveData<Int> {
        return pref.getId().asLiveData()
    }

    fun getImage(): LiveData<String> {
        return pref.getImage().asLiveData()
    }

    fun getPassword(): LiveData<String> {
        return pref.getPassword().asLiveData()
    }

    fun getEmailUsername(): LiveData<String> {
        return pref.getEmail().asLiveData()
    }

    fun getAddressUsername(): LiveData<String> {
        return pref.getAddress().asLiveData()
    }

    fun getFullnameUsername(): LiveData<String> {
        return pref.getFullname().asLiveData()
    }

    fun getDateUsername(): LiveData<String> {
        return pref.getDate().asLiveData()
    }

    fun getUsernameUsername(): LiveData<String> {
        return pref.getUserName().asLiveData()
    }


    fun clearDataUser() {
        viewModelScope.launch {
            pref.logoutUser()
        }
    }

}