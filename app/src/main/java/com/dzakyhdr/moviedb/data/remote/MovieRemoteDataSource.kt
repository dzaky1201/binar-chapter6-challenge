package com.dzakyhdr.moviedb.data.remote

import com.dzakyhdr.moviedb.data.remote.model.popular.Result
import com.dzakyhdr.moviedb.network.ApiClient

class MovieRemoteDataSource {

    suspend fun getMovies(): List<Result>? {
        try {
             return ApiClient.instance.getPopular().body()?.results
        }catch (cause: Throwable){
            throw ErrorMovie("Data Gagal Diload", cause)
        }

    }

    suspend fun getDetail(id: Int): Result {
       try {
           return ApiClient.instance.getDetail(id).body()!!
       } catch (cause: Throwable){
           throw ErrorMovie("Ada kesalahan saat load detail", cause)
       }
    }
}

class ErrorMovie(message: String, cause: Throwable?) : Throwable(message, cause)


interface MovieCallbackDetail {
    fun onComplete(result: Result)
    fun onFailure(cause: Throwable)
}