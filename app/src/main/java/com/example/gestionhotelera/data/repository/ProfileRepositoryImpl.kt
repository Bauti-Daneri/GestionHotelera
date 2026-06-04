package com.example.gestionhotelera.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.gestionhotelera.domain.model.User
import com.example.gestionhotelera.domain.repository.AuthRepository
import com.example.gestionhotelera.domain.repository.ProfileRepository
import com.example.gestionhotelera.domain.repository.UserRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val Context.settingsDataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class ProfileRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository
) : ProfileRepository {

    private val DARK_MODE_KEY = booleanPreferencesKey("dark_mode")

    override val isDarkMode: Flow<Boolean> = context.settingsDataStore.data.map { prefs ->
        prefs[DARK_MODE_KEY] ?: false
    }

    override fun getProfile(): Flow<User?> = authRepository.currentUser

    override suspend fun updateProfile(user: User) {
        userRepository.saveUser(user)
    }

    override suspend fun setDarkMode(enabled: Boolean) {
        context.settingsDataStore.edit { prefs ->
            prefs[DARK_MODE_KEY] = enabled
        }
    }
}
