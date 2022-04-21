package com.dzakyhdr.moviedb.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dzakyhdr.moviedb.data.local.auth.User
import com.dzakyhdr.moviedb.utils.SharedPreference

class ProfileViewModel(private val sharedPreference: SharedPreference) : ViewModel() {
    private var _email = MutableLiveData<String>()
    val email: LiveData<String> get() = _email

    private var _address = MutableLiveData<String>()
    val address: LiveData<String> get() = _address

    private var _fullname = MutableLiveData<String>()
    val fullname: LiveData<String> get() = _fullname

    private var _date = MutableLiveData<String>()
    val date: LiveData<String> get() = _date

    private var _username = MutableLiveData<String>()
    val username: LiveData<String> get() = _username

    fun getUserData() {
        _email.value = sharedPreference.getPrefKey("email")
        _address.value = sharedPreference.getPrefKey("address")
        _fullname.value = sharedPreference.getPrefKey("fullname")
        _date.value = sharedPreference.getPrefKey("date")
        _username.value = sharedPreference.getPrefKey("username")
    }
}