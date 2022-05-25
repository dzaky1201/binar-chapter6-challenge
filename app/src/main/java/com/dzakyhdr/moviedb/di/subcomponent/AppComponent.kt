package com.dzakyhdr.moviedb.di.subcomponent

import com.dzakyhdr.moviedb.di.*
import com.dzakyhdr.moviedb.di.viewmodel_module.*
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        DatabaseModule::class,
        DataStoreModule::class,
        LocalMovieModule::class,
        NetworkModule::class,
        RemoteMovieModule::class,
        RepositoryModule::class
    ]
)
interface AppComponent{
    fun detailSubComponent(): DetailSubComponent.Factory
    fun favoriteSubComponent(): FavoriteSubComponent.Factory
    fun homeSubComponent(): HomeSubComponent.Factory
    fun loginSubComponent(): LoginSubComponent.Factory
    fun profileSubComponent(): ProfileSubComponent.Factory
    fun registerSubComponent(): RegisterSubComponent.Factory
    fun updateSubComponent(): UpdateSubComponent.Factory
}