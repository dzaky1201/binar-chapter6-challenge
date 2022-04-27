package com.dzakyhdr.moviedb.data.remote

import com.dzakyhdr.moviedb.data.remote.model.popular.PopularResponse
import com.dzakyhdr.moviedb.data.remote.model.popular.Result
import com.dzakyhdr.moviedb.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRemoteDataSource {

    fun getMovies(movieCallback: MovieCallback) {
        ApiClient.instance.getPopular().enqueue(object : Callback<PopularResponse> {
            override fun onResponse(
                call: Call<PopularResponse>,
                response: Response<PopularResponse>
            ) {
                when {
                    response.isSuccessful -> {
                        response.body()?.results?.let {
                            movieCallback.onComplete(it)
                        }
                    }
                    response.code() == 401 -> {
                        movieCallback.onFailure(ErrorMovie("Data Not Found", null))
                    }
                    response.code() == 404 -> {
                        movieCallback.onFailure(ErrorMovie("Data Not Found", null))
                    }
                }
            }

            override fun onFailure(call: Call<PopularResponse>, t: Throwable) {
                movieCallback.onFailure(ErrorMovie("Data gagal diload", null))
            }
        })

    }

    fun getDetail(id: Int, callbackDetail: MovieCallbackDetail) {
        ApiClient.instance.getDetail(id).enqueue(object : Callback<Result> {
            override fun onResponse(call: Call<Result>, response: Response<Result>) {
                when {
                    response.code() == 200 -> {
                        callbackDetail.onComplete(response.body()!!)
                    }
                }
            }

            override fun onFailure(call: Call<Result>, t: Throwable) {
                callbackDetail.onFailure(ErrorMovie("Detail Gagal Diload", null))
            }
        })
    }
}

class ErrorMovie(message: String, cause: Throwable?) : Throwable(message, cause)

interface MovieCallback {
    fun onComplete(result: List<Result>)
    fun onFailure(cause: Throwable)
}

interface MovieCallbackDetail {
    fun onComplete(result: Result)
    fun onFailure(cause: Throwable)
}