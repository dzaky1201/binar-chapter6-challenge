package com.dzakyhdr.moviedb.di.subcomponent

import com.dzakyhdr.moviedb.di.scope.DetailScope
import com.dzakyhdr.moviedb.di.scope.HomeScope
import com.dzakyhdr.moviedb.di.viewmodel_module.HomeModule
import com.dzakyhdr.moviedb.ui.home.HomeFragment
import dagger.Subcomponent

@HomeScope
@Subcomponent(modules = [HomeModule::class])
interface HomeSubComponent {
    fun inject(fragment: HomeFragment)

    @Subcomponent.Factory
    interface Factory{
        fun create(): HomeSubComponent
    }
}