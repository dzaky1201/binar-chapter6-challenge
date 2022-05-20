package com.dzakyhdr.moviedb

import android.app.Application
import com.dzakyhdr.moviedb.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    userDataStoreModule,
                    localDataSourceModule,
                    remoteDataSourceModule,
                    repositoryModuleUser,
                    repositoryModuleMovie,
                    viewModelModule
                )
            )
        }
    }
}