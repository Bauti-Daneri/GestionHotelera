package com.example.gestionhotelera.domain.usecase.profile

import com.example.gestionhotelera.domain.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDarkModeUseCase @Inject constructor(
    private val profileRepository: ProfileRepository
) {
    operator fun invoke(): Flow<Boolean> {
        return profileRepository.isDarkMode
    }
}