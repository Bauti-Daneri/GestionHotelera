package com.example.gestionhotelera.domain.usecase.user

import com.example.gestionhotelera.domain.repository.UserRepository
import javax.inject.Inject

class CheckEmailAvailableUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(email: String, excludeUserId: String? = null): Boolean {
        if (email.isBlank()) return true
        val user = repository.getUserByEmail(email.trim().lowercase())
        return user == null || user.id == excludeUserId
    }
}
