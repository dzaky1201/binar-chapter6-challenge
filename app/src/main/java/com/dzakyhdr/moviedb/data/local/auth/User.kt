package com.dzakyhdr.moviedb.data.local.auth

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "email")
    val email: String,
    @ColumnInfo(name = "username")
    val username: String,
    @ColumnInfo(name = "fullname")
    val fullname: String = "",
    @ColumnInfo(name = "ttl")
    val ttl: String,
    @ColumnInfo(name = "address")
    val address: String,
    @ColumnInfo(name = "password")
    val password: String
) : Parcelable
