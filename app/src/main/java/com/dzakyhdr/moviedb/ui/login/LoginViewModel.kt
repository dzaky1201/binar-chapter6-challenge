package com.dzakyhdr.moviedb.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dzakyhdr.moviedb.data.local.auth.User
import com.dzakyhdr.moviedb.data.local.auth.UserRepository
import com.dzakyhdr.moviedb.resource.Resource
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

class LoginViewModel(private val repository: UserRepository) : ViewModel() {

    private var _loginStatus = MutableLiveData<Resource<User>>()
    val loginStatus: LiveData<Resource<User>> get() = _loginStatus


    fun login(email: String, password: String) {
        viewModelScope.launch {
            _loginStatus.postValue(Resource.loading(null))
            try {
                val data = repository.verifyLogin(email, password)
                _loginStatus.postValue(Resource.success(data, 0))
            } catch (exception: Exception) {
                _loginStatus.postValue(Resource.error(null, exception.message!!))
            }
        }
    }

}