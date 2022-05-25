package com.dzakyhdr.moviedb.di.viewmodel_module

import com.dzakyhdr.moviedb.data.local.auth.UserRepository
import com.dzakyhdr.moviedb.di.scope.DetailScope
import com.dzakyhdr.moviedb.di.scope.UpdateScope
import com.dzakyhdr.moviedb.ui.viewmodelfactory.UpdateViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class UpdateModule {
    @UpdateScope
    @Provides
    fun provideUpdateViewModelFactory(
        userRepository: UserRepository,
    ): UpdateViewModelFactory {
        return UpdateViewModelFactory(userRepository)
    }
}