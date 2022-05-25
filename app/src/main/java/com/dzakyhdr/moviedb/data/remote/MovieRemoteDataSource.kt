package com.dzakyhdr.moviedb.data.remote

import com.dzakyhdr.moviedb.data.remote.model.popular.Result
import com.dzakyhdr.moviedb.network.ApiService
import javax.inject.Inject

class MovieRemoteDataSource(private val service: ApiService, private val apiKey: String) {

    suspend fun getMovies(): List<Result>? {
        try {
            return service.getPopular(apiKey).results
        } catch (cause: Throwable) {
            throw ErrorMovie("Data Gagal Diload", cause)
        }

    }

    suspend fun getDetail(id: Int): Result {
        try {
            return service.getDetail(movieId = id, api = apiKey).body()!!
        } catch (cause: Throwable) {
            throw ErrorMovie("Ada kesalahan saat load detail", cause)
        }
    }
}

class ErrorMovie(message: String, cause: Throwable?) : Throwable(message, cause)