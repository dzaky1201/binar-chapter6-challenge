package com.dzakyhdr.moviedb.di.viewmodel_module

import com.dzakyhdr.moviedb.data.remote.MovieRepository
import com.dzakyhdr.moviedb.di.scope.DetailScope
import com.dzakyhdr.moviedb.ui.viewmodelfactory.DetailViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class DetailModule {

    @DetailScope
    @Provides
    fun provideDetailViewModelFactory(
        movieRepository: MovieRepository,
    ): DetailViewModelFactory {
        return DetailViewModelFactory(movieRepository)
    }
}