package com.example.gestionhotelera.domain.usecase.auth

import com.example.gestionhotelera.domain.model.User
import com.example.gestionhotelera.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCurrentUserUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    operator fun invoke(): Flow<User?> = repository.currentUser
}
