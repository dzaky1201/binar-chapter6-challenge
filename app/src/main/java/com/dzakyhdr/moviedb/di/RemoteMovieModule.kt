package com.dzakyhdr.moviedb.di

import com.dzakyhdr.moviedb.BuildConfig
import com.dzakyhdr.moviedb.data.remote.MovieRemoteDataSource
import com.dzakyhdr.moviedb.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class RemoteMovieModule {
    @Singleton
    @Provides
    fun provideMovieRemoteDataSource(apiService: ApiService): MovieRemoteDataSource{
        return MovieRemoteDataSource(apiService, BuildConfig.API_KEY)
    }
}