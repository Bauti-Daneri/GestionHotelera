package com.example.gestionhotelera.data.repository

import com.example.gestionhotelera.domain.model.User
import com.example.gestionhotelera.domain.repository.AuthRepository
import com.example.gestionhotelera.domain.repository.ProfileRepository
import com.example.gestionhotelera.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository
) : ProfileRepository {
    override fun getProfile(): Flow<User?> = authRepository.currentUser

    override suspend fun updateProfile(user: User) {
        userRepository.saveUser(user)
    }
}
