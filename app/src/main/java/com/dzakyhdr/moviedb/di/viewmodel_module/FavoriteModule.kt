package com.dzakyhdr.moviedb.di.viewmodel_module

import com.dzakyhdr.moviedb.data.remote.MovieRepository
import com.dzakyhdr.moviedb.di.scope.DetailScope
import com.dzakyhdr.moviedb.di.scope.FavoriteScope
import com.dzakyhdr.moviedb.ui.viewmodelfactory.FavoriteViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class FavoriteModule {

    @FavoriteScope
    @Provides
    fun provideFavoriteViewModelFactory(
        movieRepository: MovieRepository,
    ): FavoriteViewModelFactory {
        return FavoriteViewModelFactory(movieRepository)
    }
}