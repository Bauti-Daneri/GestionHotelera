package com.example.gestionhotelera.domain.repository

import com.example.gestionhotelera.domain.model.User
import com.example.gestionhotelera.domain.model.UserRole
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    val currentUser: Flow<User?>
    suspend fun login(email: String): Result<User>
    suspend fun logout()
}
