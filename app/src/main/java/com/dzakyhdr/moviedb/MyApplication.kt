package com.dzakyhdr.moviedb

import android.app.Application
import com.dzakyhdr.moviedb.di.Injector
import com.dzakyhdr.moviedb.di.NetworkModule
import com.dzakyhdr.moviedb.di.RemoteMovieModule
import com.dzakyhdr.moviedb.di.subcomponent.*
import com.dzakyhdr.moviedb.di.viewmodel_module.AppModule

class MyApplication : Application(), Injector {

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(applicationContext))
            .networkModule(NetworkModule(BuildConfig.BASE_URL))
            .remoteMovieModule(RemoteMovieModule(BuildConfig.API_KEY))
            .build()



    }

    override fun createDetailSubComponent(): DetailSubComponent {
        return appComponent.detailSubComponent().create()
    }

    override fun createFavoriteSubComponent(): FavoriteSubComponent {
        return appComponent.favoriteSubComponent().create()
    }

    override fun createHomeSubComponent(): HomeSubComponent {
        return appComponent.homeSubComponent().create()
    }

    override fun createLoginSubComponent(): LoginSubComponent {
        return appComponent.loginSubComponent().create()
    }

    override fun createProfilenSubComponent(): ProfileSubComponent {
        return appComponent.profileSubComponent().create()
    }

    override fun createRegisterSubComponent(): RegisterSubComponent {
        return appComponent.registerSubComponent().create()
    }

    override fun createUpdateSubComponent(): UpdateSubComponent {
        return appComponent.updateSubComponent().create()
    }
}