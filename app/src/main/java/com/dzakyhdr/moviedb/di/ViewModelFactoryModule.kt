package com.dzakyhdr.moviedb.di

import com.dzakyhdr.moviedb.data.local.auth.UserRepository
import com.dzakyhdr.moviedb.data.remote.MovieRepository
import com.dzakyhdr.moviedb.ui.viewmodelfactory.*
import com.dzakyhdr.moviedb.utils.UserDataStoreManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class ViewModelFactoryModule {
    @Singleton
    @Provides
    fun provideUpdateViewModelFactory(
        userRepository: UserRepository,
    ): UpdateViewModelFactory {
        return UpdateViewModelFactory(userRepository)
    }

    @Singleton
    @Provides
    fun provideRegisterViewModelFactory(
        userRepository: UserRepository,
    ): RegisterViewModelFactory {
        return RegisterViewModelFactory(userRepository)
    }

    @Singleton
    @Provides
    fun provideProfileViewModelFactory(
        userRepository: UserRepository,
        pref: UserDataStoreManager
    ): ProfileViewModelFactory {
        return ProfileViewModelFactory(userRepository, pref)
    }

    @Singleton
    @Provides
    fun provideLoginViewModelFactory(
        userRepository: UserRepository,
        pref: UserDataStoreManager
    ): LoginViewModelFactory {
        return LoginViewModelFactory(userRepository, pref)
    }

    @Singleton
    @Provides
    fun provideHomeViewModelFactory(
        movieRepository: MovieRepository,
        userRepository: UserRepository,
        pref: UserDataStoreManager
    ): HomeViewModelFactory {
        return HomeViewModelFactory(movieRepository, userRepository, pref)
    }

    @Singleton
    @Provides
    fun provideFavoriteViewModelFactory(
        movieRepository: MovieRepository,
    ): FavoriteViewModelFactory {
        return FavoriteViewModelFactory(movieRepository)
    }

    @Singleton
    @Provides
    fun provideDetailViewModelFactory(
        movieRepository: MovieRepository,
    ): DetailViewModelFactory {
        return DetailViewModelFactory(movieRepository)
    }
}