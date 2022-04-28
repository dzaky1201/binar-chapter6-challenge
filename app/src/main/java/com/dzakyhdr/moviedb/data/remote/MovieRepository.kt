package com.dzakyhdr.moviedb.data.remote

import com.dzakyhdr.moviedb.data.remote.model.popular.Result
import com.dzakyhdr.moviedb.network.ApiClient

class MovieRepository(private val movieRemoteDataSource: MovieRemoteDataSource) {

    suspend fun getPopularMovie(): List<Result>? {
        return movieRemoteDataSource.getMovies()
    }

    suspend fun getDetail(id: Int): Result {
        return movieRemoteDataSource.getDetail(id)
    }
}