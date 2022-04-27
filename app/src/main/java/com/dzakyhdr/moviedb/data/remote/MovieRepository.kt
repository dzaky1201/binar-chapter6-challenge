package com.dzakyhdr.moviedb.data.remote

import com.dzakyhdr.moviedb.data.remote.model.popular.Result
import com.dzakyhdr.moviedb.network.ApiClient

class MovieRepository(private val movieRemoteDataSource: MovieRemoteDataSource) {

    fun getPopularMovie(movieCallback: MovieCallback) {
        movieRemoteDataSource.getMovies(movieCallback)
    }

    fun getDetail(id: Int, movieCallbackDetail: MovieCallbackDetail) {
        movieRemoteDataSource.getDetail(id, movieCallbackDetail)
    }
}