package com.example.gestionhotelera.domain.usecase.auth

import com.example.gestionhotelera.domain.model.User
import com.example.gestionhotelera.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(email: String): Result<User> = repository.login(email)
}
