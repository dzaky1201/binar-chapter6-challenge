package com.dzakyhdr.moviedb.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dzakyhdr.moviedb.data.remote.model.popular.PopularResponse
import com.dzakyhdr.moviedb.data.remote.model.popular.Result
import com.dzakyhdr.moviedb.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    private var _popular = MutableLiveData<List<Result>>()
    val popular: LiveData<List<Result>> get() = _popular

    private var _errorStatus = MutableLiveData<Boolean>()
    val errorStatus: LiveData<Boolean> get() = _errorStatus

    private var _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    init {
        getPopularMovie()
    }


    fun getPopularMovie() {
        ApiClient.instance.getPopular().enqueue(object : Callback<PopularResponse> {
            override fun onResponse(call: Call<PopularResponse>, response: Response<PopularResponse>) {
                _loading.value = true
                when {
                    response.isSuccessful -> {
                        response.body()?.results?.let {
                        _loading.value = false
                        _popular.value = it
                        _errorStatus.value = false
                        }
                    }
                    response.code() == 401 -> {
                        _loading.value = false
                        _errorStatus.value = true
                    }
                    response.code() == 404 -> {
                        _loading.value = false
                        _errorStatus.value = true
                    }
                }
            }

            override fun onFailure(call: Call<PopularResponse>, t: Throwable) {
                _errorStatus.value = true
                _loading.value = false
            }
        })
    }
}