package com.dzakyhdr.moviedb.di

import com.dzakyhdr.moviedb.data.local.MovieLocalDataSource
import com.dzakyhdr.moviedb.data.local.favorite.MovieDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalMovieModule {

    @Singleton
    @Provides
    fun provideMovieLocalDataSource(movieDao: MovieDao): MovieLocalDataSource{
        return MovieLocalDataSource(movieDao)
    }
}