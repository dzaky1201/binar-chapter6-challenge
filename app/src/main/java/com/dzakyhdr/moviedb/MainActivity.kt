package com.dzakyhdr.moviedb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.dzakyhdr.moviedb.ui.detail.DetailViewModel
import com.dzakyhdr.moviedb.ui.favorite.FavoriteViewModel
import com.dzakyhdr.moviedb.ui.home.HomeViewModel
import com.dzakyhdr.moviedb.ui.login.LoginViewModel
import com.dzakyhdr.moviedb.ui.profile.ProfileViewModel
import com.dzakyhdr.moviedb.ui.profile.UpdateProfileViewModel
import com.dzakyhdr.moviedb.ui.register.RegisterViewModel
import com.dzakyhdr.moviedb.ui.viewmodelfactory.*
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var loginViewModelFactory: LoginViewModelFactory
    val loginViewModel: LoginViewModel by viewModels{
        loginViewModelFactory
    }

    @Inject
    lateinit var detailViewModelFactory: DetailViewModelFactory
    val detailViewModel: DetailViewModel by viewModels {
        detailViewModelFactory
    }

    @Inject
    lateinit var favoriteViewModelFactory: FavoriteViewModelFactory
    val favoriteViewModel: FavoriteViewModel by viewModels {
        favoriteViewModelFactory
    }

    @Inject
    lateinit var homeViewModelFactory: HomeViewModelFactory
    val homeViewModel: HomeViewModel by viewModels{
        homeViewModelFactory
    }

    @Inject
    lateinit var profileViewModelFactory: ProfileViewModelFactory
    val profileViewModel: ProfileViewModel by viewModels{
        profileViewModelFactory
    }

    @Inject
    lateinit var updateViewModelFactory: UpdateViewModelFactory
    val updateProfileViewModel: UpdateProfileViewModel by viewModels{
        updateViewModelFactory
    }

    @Inject
    lateinit var registerViewModelFactory: RegisterViewModelFactory
    val registerViewModel: RegisterViewModel by viewModels {
        registerViewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

}