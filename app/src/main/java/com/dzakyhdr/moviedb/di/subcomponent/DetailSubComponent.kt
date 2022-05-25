package com.dzakyhdr.moviedb.di.subcomponent

import com.dzakyhdr.moviedb.di.scope.DetailScope
import com.dzakyhdr.moviedb.di.viewmodel_module.DetailModule
import com.dzakyhdr.moviedb.ui.detail.DetailFragment
import dagger.Subcomponent

@DetailScope
@Subcomponent(modules = [DetailModule::class])
interface DetailSubComponent {
    fun inject(fragment: DetailFragment)

    @Subcomponent.Factory
    interface Factory{
        fun create(): DetailSubComponent
    }
}