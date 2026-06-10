package com.example.gestionhotelera.domain.usecase.user

import com.example.gestionhotelera.domain.model.ValidationResult
import javax.inject.Inject

class ValidatePasswordUseCase @Inject constructor() {
    operator fun invoke(password: String, isRequired: Boolean = true): ValidationResult {
        if (!isRequired && password.isEmpty()) {
            return ValidationResult.Success
        }
        if (password.isBlank()) {
            return ValidationResult.Error("La contraseña es obligatoria")
        }
        if (password.length < 6) {
            return ValidationResult.Error("La contraseña debe tener al menos 6 caracteres")
        }
        return ValidationResult.Success
    }
}
