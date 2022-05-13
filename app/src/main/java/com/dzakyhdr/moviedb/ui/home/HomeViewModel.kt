package com.dzakyhdr.moviedb.ui.home

import androidx.lifecycle.*
import com.dzakyhdr.moviedb.data.remote.ErrorMovie
import com.dzakyhdr.moviedb.data.remote.MovieRepository
import com.dzakyhdr.moviedb.data.remote.model.popular.Result
import com.dzakyhdr.moviedb.utils.UserDataStoreManager
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: MovieRepository,
    private val pref: UserDataStoreManager
    )
    : ViewModel() {

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
        viewModelScope.launch {
            try {
                _loading.value = true
                _popular.value = repository.getPopularMovie()
            }catch (error: ErrorMovie){
                _errorStatus.value = error.message
            }finally {
                _loading.value = false
            }
        }
    }

    fun onSnackbarShown() {
        _errorStatus.value = null
    }

    fun getUserName(): LiveData<String>{
        return pref.getUserName().asLiveData()
    }

}