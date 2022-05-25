package com.dzakyhdr.moviedb.di

import com.dzakyhdr.moviedb.data.remote.MovieRemoteDataSource
import com.dzakyhdr.moviedb.network.ApiService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RemoteMovieModule(private val apiKey: String) {
    @Singleton
    @Provides
    fun provideMovieRemoteDataSource(apiService: ApiService): MovieRemoteDataSource{
        return MovieRemoteDataSource(apiService, apiKey)
    }
}