package com.dzakyhdr.moviedb.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dzakyhdr.moviedb.data.remote.model.popular.Result
import com.dzakyhdr.moviedb.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel : ViewModel() {

    private var _detail = MutableLiveData<Result?>()
    val detail: LiveData<Result?> get() = _detail

    private var _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading


    fun getDetail(id: Int) {
        ApiClient.instance.getDetail(id).enqueue(object : Callback<Result> {
            override fun onResponse(call: Call<Result>, response: Response<Result>) {
                _loading.value = true
                when {
                    response.code() == 200 -> {
                        _loading.value = false
                        _detail.value = response.body()
                    }
                }
            }

            override fun onFailure(call: Call<Result>, t: Throwable) {
                _loading.value = false
            }
        })
    }


}