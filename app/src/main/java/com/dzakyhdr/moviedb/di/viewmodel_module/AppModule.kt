package com.dzakyhdr.moviedb.di.viewmodel_module

import android.content.Context
import com.dzakyhdr.moviedb.di.subcomponent.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(
    subcomponents = [
        DetailSubComponent::class,
        FavoriteSubComponent::class,
        HomeSubComponent::class,
        LoginSubComponent::class,
        ProfileSubComponent::class,
        RegisterSubComponent::class,
        UpdateSubComponent::class]
)
class AppModule(private val context: Context) {
    @Singleton
    @Provides
    fun provideApplicationContext(): Context {
        return context.applicationContext
    }
}