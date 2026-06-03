package com.example.gestionhotelera.domain.usecase.profile

import com.example.gestionhotelera.domain.model.User
import com.example.gestionhotelera.domain.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProfileUseCase @Inject constructor(
    private val repository: ProfileRepository
) {
    operator fun invoke(): Flow<User?> = repository.getProfile()
}
