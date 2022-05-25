package com.dzakyhdr.moviedb.di

import com.dzakyhdr.moviedb.di.subcomponent.*

interface Injector{
    fun createDetailSubComponent(): DetailSubComponent
    fun createFavoriteSubComponent(): FavoriteSubComponent
    fun createHomeSubComponent(): HomeSubComponent
    fun createLoginSubComponent(): LoginSubComponent
    fun createProfilenSubComponent(): ProfileSubComponent
    fun createRegisterSubComponent(): RegisterSubComponent
    fun createUpdateSubComponent(): UpdateSubComponent
}