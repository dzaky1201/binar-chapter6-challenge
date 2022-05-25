package com.dzakyhdr.moviedb.di.subcomponent

import com.dzakyhdr.moviedb.di.scope.DetailScope
import com.dzakyhdr.moviedb.di.scope.LoginScope
import com.dzakyhdr.moviedb.di.viewmodel_module.LoginModule
import com.dzakyhdr.moviedb.ui.login.LoginFragment
import dagger.Subcomponent

@LoginScope
@Subcomponent(modules = [LoginModule::class])
interface LoginSubComponent {
    fun inject(fragment: LoginFragment)

    @Subcomponent.Factory
    interface Factory{
        fun create(): LoginSubComponent
    }
}