package com.dzakyhdr.moviedb.di

import android.content.Context
import com.dzakyhdr.moviedb.data.local.auth.UserRepository

object Injection {

    fun provideRepositoryUser(context: Context): UserRepository? {
        return UserRepository.getInstance(context)
    }
}