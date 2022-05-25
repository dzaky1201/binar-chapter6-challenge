package com.dzakyhdr.moviedb.di

import android.content.Context
import com.dzakyhdr.moviedb.utils.UserDataStoreManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataStoreModule {

    @Singleton
    @Provides
    fun provideUserDataStoreManager(context: Context): UserDataStoreManager {
        return UserDataStoreManager(context)
    }
}