package com.example.gestionhotelera.domain.usecase.profile

import com.example.gestionhotelera.domain.repository.ProfileRepository
import javax.inject.Inject

class SetDarkModeUseCase @Inject constructor(
    private val repository: ProfileRepository
) {
    suspend operator fun invoke(enabled: Boolean) {
        repository.setDarkMode(enabled)
    }
}