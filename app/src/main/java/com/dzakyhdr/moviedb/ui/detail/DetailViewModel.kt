package com.dzakyhdr.moviedb.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dzakyhdr.moviedb.data.remote.ErrorMovie
import com.dzakyhdr.moviedb.data.remote.MovieCallbackDetail
import com.dzakyhdr.moviedb.data.remote.MovieRepository
import com.dzakyhdr.moviedb.data.remote.model.popular.Result
import com.dzakyhdr.moviedb.network.ApiClient
import com.dzakyhdr.moviedb.network.ApiService
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel(private val repository: MovieRepository) : ViewModel() {

    private var _detail = MutableLiveData<Result?>()
    val detail: LiveData<Result?> get() = _detail

    private var _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    private var _errorStatus = MutableLiveData<String?>()
    val errorStatus: LiveData<String?> get() = _errorStatus



    fun getDetail(id: Int) {
      viewModelScope.launch {
          try {
              _loading.value = true
              _detail.value = repository.getDetail(id)
          } catch (error: ErrorMovie){
              _errorStatus.value = error.message
          }finally {
              _loading.value = false
          }
      }
    }

    fun onSnackbarShown() {
        _errorStatus.value = null
    }


}