package com.example.gestionhotelera.domain.repository

import com.example.gestionhotelera.domain.model.User
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {
    fun getProfile(): Flow<User?>
    suspend fun updateProfile(user: User)
}
