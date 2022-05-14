package com.dzakyhdr.moviedb

import android.app.Application
import com.dzakyhdr.moviedb.data.remote.MovieRemoteDataSource
import com.dzakyhdr.moviedb.data.remote.MovieRepository
import com.dzakyhdr.moviedb.utils.UserDataStoreManager

class MyApplication: Application() {

    private val movieRemoteDataSource by lazy { MovieRemoteDataSource() }
    val repository by lazy { MovieRepository(movieRemoteDataSource) }

}