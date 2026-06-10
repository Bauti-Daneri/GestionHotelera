package com.example.gestionhotelera.domain.usecase.user

import com.example.gestionhotelera.domain.model.ValidationResult
import javax.inject.Inject

class ValidatePhoneUseCase @Inject constructor() {
    operator fun invoke(phone: String): ValidationResult {
        if (phone.isBlank()) {
            return ValidationResult.Error("El teléfono es obligatorio")
        }
        val digitsOnly = phone.replace(Regex("[^0-9]"), "")
        if (digitsOnly.length < 8) {
            return ValidationResult.Error("El teléfono debe tener al menos 8 dígitos")
        }
        return ValidationResult.Success
    }
}
