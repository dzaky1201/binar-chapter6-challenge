package com.dzakyhdr.moviedb.di

import android.content.Context
import androidx.room.Room
import com.dzakyhdr.moviedb.data.local.auth.MovieDatabase
import com.dzakyhdr.moviedb.data.local.auth.UserDao
import com.dzakyhdr.moviedb.data.local.favorite.MovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideMovieDatabase(@ApplicationContext context: Context): MovieDatabase {
        return Room.databaseBuilder(context, MovieDatabase::class.java, "movie").build()
    }

    @Singleton
    @Provides
    fun provideMovieDao(movieDatabase: MovieDatabase): MovieDao{
        return movieDatabase.movieDao()
    }

    @Singleton
    @Provides
    fun provideUserDao(movieDatabase: MovieDatabase): UserDao{
        return movieDatabase.userDao()
    }
}