package com.example.gestionhotelera.domain.usecase.user

import com.example.gestionhotelera.domain.repository.UserRepository
import javax.inject.Inject

class CheckPhoneAvailableUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(phone: String, excludeUserId: String? = null): Boolean {
        if (phone.isBlank()) return true
        val normalizedPhone = phone.replace(Regex("[^0-9]"), "")
        val user = repository.getUserByPhone(normalizedPhone)
        return user == null || user.id == excludeUserId
    }
}
