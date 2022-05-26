package com.dzakyhdr.moviedb.di

import com.dzakyhdr.moviedb.data.local.MovieLocalDataSource
import com.dzakyhdr.moviedb.data.local.favorite.MovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class LocalMovieModule {

    @Singleton
    @Provides
    fun provideMovieLocalDataSource(movieDao: MovieDao): MovieLocalDataSource{
        return MovieLocalDataSource(movieDao)
    }
}