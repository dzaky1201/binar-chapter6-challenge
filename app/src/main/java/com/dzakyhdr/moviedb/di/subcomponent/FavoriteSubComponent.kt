package com.dzakyhdr.moviedb.di.subcomponent

import com.dzakyhdr.moviedb.di.scope.DetailScope
import com.dzakyhdr.moviedb.di.scope.FavoriteScope
import com.dzakyhdr.moviedb.di.viewmodel_module.FavoriteModule
import com.dzakyhdr.moviedb.ui.favorite.FavoriteFragment
import dagger.Subcomponent

@FavoriteScope
@Subcomponent(modules = [FavoriteModule::class])
interface FavoriteSubComponent {
    fun inject(fragment: FavoriteFragment)

    @Subcomponent.Factory
    interface Factory{
        fun create(): FavoriteSubComponent
    }
}