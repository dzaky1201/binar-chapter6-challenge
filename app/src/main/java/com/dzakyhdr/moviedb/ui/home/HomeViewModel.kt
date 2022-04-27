package com.dzakyhdr.moviedb.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dzakyhdr.moviedb.data.remote.ErrorMovie
import com.dzakyhdr.moviedb.data.remote.MovieCallback
import com.dzakyhdr.moviedb.data.remote.MovieRepository
import com.dzakyhdr.moviedb.data.remote.model.popular.Result
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: MovieRepository) : ViewModel() {

    private var _popular = MutableLiveData<List<Result>>()
    val popular: LiveData<List<Result>> get() = _popular

    private var _errorStatus = MutableLiveData<String?>()
    val errorStatus: LiveData<String?> get() = _errorStatus

    private var _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading


    init {
        getPopularMovie()
    }


    fun getPopularMovie() {
        _loading.value = true
        try {
            repository.getPopularMovie(object : MovieCallback {
                override fun onComplete(result: List<Result>) {
                    _loading.postValue(false)
                    _popular.value = result
                }

                override fun onFailure(cause: Throwable) {
                    _loading.postValue(false)
                    _errorStatus.value = cause.message
                }
            })
        } catch (error: ErrorMovie) {
            _errorStatus.value = error.message
        } finally {
            _loading.value = false
        }
    }

    fun onSnackbarShown() {
        _errorStatus.value = null
    }
}