package com.hotelops.app.domain.usecases

import com.hotelops.app.domain.repositories.UserRepository
import javax.inject.Inject

/**
 * Caso de uso para login
 * Lógica pura: sin conocimiento de UI, BD, ni API
 */
class LoginUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(email: String, password: String) = 
        userRepository.login(email, password)
}

