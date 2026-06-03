package com.example.gestionhotelera.domain.repository

import com.example.gestionhotelera.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUsers(): Flow<List<User>>
    suspend fun getUserById(id: String): User?
    suspend fun saveUser(user: User)
    suspend fun deleteUser(user: User)
}
