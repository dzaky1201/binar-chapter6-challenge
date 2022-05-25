package com.dzakyhdr.moviedb.di.subcomponent

import com.dzakyhdr.moviedb.di.scope.DetailScope
import com.dzakyhdr.moviedb.di.scope.UpdateScope
import com.dzakyhdr.moviedb.di.viewmodel_module.UpdateModule
import com.dzakyhdr.moviedb.ui.profile.UpdateProfileFragment
import dagger.Subcomponent

@UpdateScope
@Subcomponent(modules = [UpdateModule::class])
interface UpdateSubComponent {
    fun inject(fragment: UpdateProfileFragment)

    @Subcomponent.Factory
    interface Factory{
        fun create(): UpdateSubComponent
    }
}