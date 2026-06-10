package com.example.gestionhotelera.domain.usecase.user

import com.example.gestionhotelera.domain.model.ValidationResult
import javax.inject.Inject

class ValidateEmployeeNameUseCase @Inject constructor() {
    operator fun invoke(name: String): ValidationResult {
        if (name.isBlank()) {
            return ValidationResult.Error("El nombre es obligatorio")
        }
        if (name.trim().length < 2) {
            return ValidationResult.Error("El nombre debe tener al menos 2 caracteres")
        }
        return ValidationResult.Success
    }
}
