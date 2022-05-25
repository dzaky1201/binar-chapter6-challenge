package com.dzakyhdr.moviedb.di.viewmodel_module

import com.dzakyhdr.moviedb.data.local.auth.UserRepository
import com.dzakyhdr.moviedb.data.remote.MovieRepository
import com.dzakyhdr.moviedb.di.scope.DetailScope
import com.dzakyhdr.moviedb.di.scope.HomeScope
import com.dzakyhdr.moviedb.ui.viewmodelfactory.HomeViewModelFactory
import com.dzakyhdr.moviedb.utils.UserDataStoreManager
import dagger.Module
import dagger.Provides

@Module
class HomeModule {

    @HomeScope
    @Provides
    fun provideHomeViewModelFactory(
        movieRepository: MovieRepository,
        userRepository: UserRepository,
        pref: UserDataStoreManager
    ): HomeViewModelFactory{
        return HomeViewModelFactory(movieRepository, userRepository, pref)
    }
}