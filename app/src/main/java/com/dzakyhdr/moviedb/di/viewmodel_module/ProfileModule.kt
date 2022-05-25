package com.dzakyhdr.moviedb.di.viewmodel_module

import com.dzakyhdr.moviedb.data.local.auth.UserRepository
import com.dzakyhdr.moviedb.di.scope.DetailScope
import com.dzakyhdr.moviedb.di.scope.ProfileScope
import com.dzakyhdr.moviedb.ui.viewmodelfactory.ProfileViewModelFactory
import com.dzakyhdr.moviedb.utils.UserDataStoreManager
import dagger.Module
import dagger.Provides

@Module
class ProfileModule {
    @ProfileScope
    @Provides
    fun provideProfileViewModelFactory(
        userRepository: UserRepository,
        pref: UserDataStoreManager
    ): ProfileViewModelFactory {
        return ProfileViewModelFactory(userRepository, pref)
    }
}