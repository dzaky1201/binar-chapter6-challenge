package com.dzakyhdr.moviedb.di.viewmodel_module

import com.dzakyhdr.moviedb.data.local.auth.UserRepository
import com.dzakyhdr.moviedb.di.scope.DetailScope
import com.dzakyhdr.moviedb.di.scope.LoginScope
import com.dzakyhdr.moviedb.ui.viewmodelfactory.LoginViewModelFactory
import com.dzakyhdr.moviedb.utils.UserDataStoreManager
import dagger.Module
import dagger.Provides

@Module
class LoginModule {
    @LoginScope
    @Provides
    fun provideLoginViewModelFactory(
        userRepository: UserRepository,
        pref: UserDataStoreManager
    ): LoginViewModelFactory {
        return LoginViewModelFactory(userRepository, pref)
    }
}