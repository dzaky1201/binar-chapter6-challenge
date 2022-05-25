package com.dzakyhdr.moviedb.di.subcomponent

import com.dzakyhdr.moviedb.di.scope.DetailScope
import com.dzakyhdr.moviedb.di.scope.ProfileScope
import com.dzakyhdr.moviedb.di.viewmodel_module.ProfileModule
import com.dzakyhdr.moviedb.ui.profile.ProfileFragment
import dagger.Subcomponent

@ProfileScope
@Subcomponent(modules = [ProfileModule::class])
interface ProfileSubComponent {
    fun inject(fragment: ProfileFragment)

    @Subcomponent.Factory
    interface Factory{
        fun create(): ProfileSubComponent
    }
}