package com.dzakyhdr.moviedb.di

import android.content.Context
import com.dzakyhdr.moviedb.utils.UserDataStoreManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class DataStoreModule {

    @Singleton
    @Provides
    fun provideUserDataStoreManager(@ApplicationContext context: Context): UserDataStoreManager {
        return UserDataStoreManager(context)
    }
}