package com.example.gestionhotelera.domain.usecase.profile

import com.example.gestionhotelera.domain.model.User
import com.example.gestionhotelera.domain.repository.ProfileRepository
import javax.inject.Inject

class UpdateProfileUseCase @Inject constructor(
    private val repository: ProfileRepository
) {
    suspend operator fun invoke(user: User) = repository.updateProfile(user)
}
