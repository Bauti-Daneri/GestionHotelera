package com.example.gestionhotelera.domain.usecase.user

import com.example.gestionhotelera.domain.model.ValidationResult
import javax.inject.Inject

class ValidateEmailUseCase @Inject constructor() {
    private val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\$".toRegex()

    operator fun invoke(email: String): ValidationResult {
        if (email.isBlank()) {
            return ValidationResult.Error("El email es obligatorio")
        }
        if (!email.trim().matches(emailRegex)) {
            return ValidationResult.Error("Formato de email inválido")
        }
        return ValidationResult.Success
    }
}
