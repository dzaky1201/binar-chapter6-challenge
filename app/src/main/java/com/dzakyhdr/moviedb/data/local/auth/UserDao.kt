package com.dzakyhdr.moviedb.data.local.auth

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import retrofit2.http.GET

@Dao
interface UserDao {

    @Insert()
    suspend fun insert(user: User)

    @Query("SELECT * FROM user WHERE email LIKE :email AND password LIKE :password")
    suspend fun readLogin(email: String, password: String): User

    @Update(onConflict = REPLACE)
    suspend fun update(user: User)
}