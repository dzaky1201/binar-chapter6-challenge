package com.dzakyhdr.moviedb.di

import android.content.Context
import com.dzakyhdr.moviedb.data.local.auth.UserDatabase
import com.dzakyhdr.moviedb.data.local.auth.UserRepository

object Injection {
    fun provideRepositoryUser(context: Context): UserRepository {
        val database = UserDatabase.getInstance(context)
        val dao = database.userDao()
        return UserRepository(dao)
    }
}