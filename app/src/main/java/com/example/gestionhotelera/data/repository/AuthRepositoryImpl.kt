package com.example.gestionhotelera.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.gestionhotelera.data.local.UserDao
import com.example.gestionhotelera.data.mapper.toDomain
import com.example.gestionhotelera.domain.model.User
import com.example.gestionhotelera.domain.repository.AuthRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "session")

class AuthRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val userDao: UserDao
) : AuthRepository {

    private val USER_ID_KEY = stringPreferencesKey("user_id")

    override val currentUser: Flow<User?> = context.dataStore.data.map { prefs ->
        val id = prefs[USER_ID_KEY]
        id?.let { userDao.getUserById(it)?.toDomain() }
    }

    override suspend fun login(email: String): Result<User> {
        val userEntity = userDao.getUserByEmail(email)
        return if (userEntity != null) {
            val user = userEntity.toDomain()
            context.dataStore.edit { it[USER_ID_KEY] = user.id }
            Result.success(user)
        } else {
            Result.failure(Exception("Usuario no encontrado"))
        }
    }

    override suspend fun logout() {
        context.dataStore.edit { it.remove(USER_ID_KEY) }
    }
}
