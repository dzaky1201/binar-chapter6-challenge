package com.dzakyhdr.moviedb.ui.home

import android.util.Log
import androidx.lifecycle.*
import com.dzakyhdr.moviedb.data.local.auth.User
import com.dzakyhdr.moviedb.data.local.auth.UserRepository
import com.dzakyhdr.moviedb.data.remote.ErrorMovie
import com.dzakyhdr.moviedb.data.remote.MovieRepository
import com.dzakyhdr.moviedb.data.remote.model.popular.Result
import com.dzakyhdr.moviedb.resource.Resource
import com.dzakyhdr.moviedb.utils.UserDataStoreManager
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel (
    private val movieRepository: MovieRepository,
    private val userRepository: UserRepository,
    private val pref: UserDataStoreManager
) : ViewModel() {

    private var _popular = MutableLiveData<List<Result>>()
    val popular: LiveData<List<Result>> get() = _popular

    private var _errorStatus = MutableLiveData<String?>()
    val errorStatus: LiveData<String?> get() = _errorStatus

    private var _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    private var _userData = MutableLiveData<Resource<User>>()
    val userData: LiveData<Resource<User>> get() = _userData

    init {
        getPopularMovie()
    }


    private fun getPopularMovie() {
        viewModelScope.launch {
            try {
                _loading.value = true
                _popular.value = movieRepository.getPopularMovie()
            } catch (error: ErrorMovie) {
                _errorStatus.value = error.message
            } finally {
                _loading.value = false
            }
        }
    }

    fun onSnackbarShown() {
        _errorStatus.value = null
    }

    fun userData(id: Int) {
        viewModelScope.launch {
            Log.d("thread", Thread.currentThread().name)
            _userData.value = Resource.loading(null)
            try {
                val data = userRepository.getUser(id)
                _userData.value = Resource.success(data, 0)
                Log.d("thread", Thread.currentThread().name)
            } catch (exception: Exception) {
                Log.d("thread", Thread.currentThread().name)
                _userData.value = Resource.error(null, exception.message!!)
            }
        }
    }

    fun getIdUser(): LiveData<Int> {
        return pref.getId().asLiveData()
    }


}