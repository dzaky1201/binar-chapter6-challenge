package com.dzakyhdr.moviedb.utils

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.dzakyhdr.moviedb.data.local.auth.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserDataStoreManager(private val context: Context) {

    suspend fun saveUser(user: User){
        context.userDataStore.edit { pref ->
            pref[ID_KEY] = user.id
            pref[EMAIL_KEY] = user.email
            pref[PASSWORD_KEY] = user.password
            pref[ADDRESS_KEY] = user.address
            pref[FULLNAME_KEY] = user.fullname
            pref[DATE_KEY] = user.ttl
            pref[USERNAME_KEY] = user.username
            pref[IMAGE_KEY] = user.image
        }
    }

    suspend fun clearUser(){
        context.userDataStore.edit { pref ->
            pref[ID_KEY] = 0
            pref[EMAIL_KEY] = ""
            pref[PASSWORD_KEY] = ""
            pref[ADDRESS_KEY] = ""
            pref[FULLNAME_KEY] = ""
            pref[DATE_KEY] = ""
            pref[USERNAME_KEY] = ""
            pref[IMAGE_KEY] = ""
        }
    }

    suspend fun saveUserStatus(status: Boolean){
        context.userDataStore.edit { pref ->
            pref[STATUS_KEY] = status
        }
    }

    fun getStatus(): Flow<Boolean>{
        return context.userDataStore.data.map { pref ->
            pref[STATUS_KEY] ?: false
        }
    }


    fun getEmail(): Flow<String>{
        return context.userDataStore.data.map { pref ->
            pref[EMAIL_KEY] ?: ""
        }
    }

    fun getImage(): Flow<String>{
        return context.userDataStore.data.map { pref ->
            pref[IMAGE_KEY] ?: ""
        }
    }

    fun getId(): Flow<Int>{
        return context.userDataStore.data.map { pref ->
            pref[ID_KEY] ?: 0
        }
    }

    fun getPassword(): Flow<String>{
        return context.userDataStore.data.map { pref ->
            pref[PASSWORD_KEY] ?: ""
        }
    }

    fun getAddress(): Flow<String>{
        return context.userDataStore.data.map { pref ->
            pref[ADDRESS_KEY] ?: ""
        }
    }

    fun getFullname(): Flow<String>{
        return context.userDataStore.data.map { pref ->
            pref[FULLNAME_KEY] ?: ""
        }
    }

    fun getDate(): Flow<String>{
        return context.userDataStore.data.map { pref ->
            pref[DATE_KEY] ?: ""
        }
    }

    fun getUserName(): Flow<String>{
        return context.userDataStore.data.map { pref ->
            pref[USERNAME_KEY] ?: ""
        }
    }

    companion object{
        private const val DATASTORE_NAME = "user_preference"
        private val EMAIL_KEY = stringPreferencesKey("email_key")
        private val ID_KEY = intPreferencesKey("id_key")
        private val PASSWORD_KEY = stringPreferencesKey("password_key")
        private val ADDRESS_KEY = stringPreferencesKey("address_key")
        private val FULLNAME_KEY = stringPreferencesKey("fullname_key")
        private val DATE_KEY = stringPreferencesKey("date_key")
        private val USERNAME_KEY = stringPreferencesKey("username_key")
        private val IMAGE_KEY = stringPreferencesKey("image_key")
        private val STATUS_KEY = booleanPreferencesKey("status_key")

        private val Context.userDataStore by preferencesDataStore(
            name = DATASTORE_NAME
        )
    }
}