package com.dzakyhdr.moviedb.data.local.auth

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import retrofit2.http.GET

@Dao
interface UserDao {

    @Insert
    suspend fun insert(user: User)

    @Query("SELECT * FROM user WHERE email LIKE :email AND password LIKE :password")
    fun readLogin(email: String, password: String): User
}