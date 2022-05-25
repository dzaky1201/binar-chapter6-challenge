package com.dzakyhdr.moviedb.di.subcomponent

import com.dzakyhdr.moviedb.di.scope.DetailScope
import com.dzakyhdr.moviedb.di.scope.RegisterScope
import com.dzakyhdr.moviedb.di.viewmodel_module.RegisterModule
import com.dzakyhdr.moviedb.ui.register.RegisterFragment
import dagger.Subcomponent

@RegisterScope
@Subcomponent(modules = [RegisterModule::class])
interface RegisterSubComponent {
    fun inject(fragment: RegisterFragment)

    @Subcomponent.Factory
    interface Factory{
        fun create(): RegisterSubComponent
    }
}