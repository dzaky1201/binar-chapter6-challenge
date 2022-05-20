package com.dzakyhdr.moviedb.di

import com.dzakyhdr.moviedb.data.local.MovieLocalDataSource
import com.dzakyhdr.moviedb.data.local.auth.UserRepository
import com.dzakyhdr.moviedb.data.remote.MovieRemoteDataSource
import com.dzakyhdr.moviedb.data.remote.MovieRepository
import com.dzakyhdr.moviedb.ui.detail.DetailViewModel
import com.dzakyhdr.moviedb.ui.favorite.FavoriteViewModel
import com.dzakyhdr.moviedb.ui.home.HomeViewModel
import com.dzakyhdr.moviedb.ui.login.LoginViewModel
import com.dzakyhdr.moviedb.ui.profile.ProfileViewModel
import com.dzakyhdr.moviedb.ui.profile.UpdateProfileViewModel
import com.dzakyhdr.moviedb.ui.register.RegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val localDataSourceModule = module {
    single { MovieLocalDataSource(get()) }
}

val remoteDataSourceModule = module {
    single { MovieRemoteDataSource() }
}

val repositoryModuleUser = module {
    single { UserRepository(get()) }
}

val repositoryModuleMovie = module {
    single { MovieRepository(get(), get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get(), get(), get()) }
    viewModel { DetailViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }
    viewModel { LoginViewModel(get(), get()) }
    viewModel { ProfileViewModel(get(), get()) }
    viewModel { UpdateProfileViewModel(get()) }
    viewModel { RegisterViewModel(get()) }
}