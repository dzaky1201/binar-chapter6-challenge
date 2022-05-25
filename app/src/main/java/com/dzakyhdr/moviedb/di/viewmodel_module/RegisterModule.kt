package com.dzakyhdr.moviedb.di.viewmodel_module

import com.dzakyhdr.moviedb.data.local.auth.UserRepository
import com.dzakyhdr.moviedb.di.scope.DetailScope
import com.dzakyhdr.moviedb.di.scope.RegisterScope
import com.dzakyhdr.moviedb.ui.viewmodelfactory.RegisterViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class RegisterModule {
    @RegisterScope
    @Provides
    fun provideRegisterViewModelFactory(
        userRepository: UserRepository,
    ): RegisterViewModelFactory {
        return RegisterViewModelFactory(userRepository)
    }
}