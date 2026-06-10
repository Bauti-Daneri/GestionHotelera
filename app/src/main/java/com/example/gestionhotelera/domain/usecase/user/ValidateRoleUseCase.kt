package com.example.gestionhotelera.domain.usecase.user

import com.example.gestionhotelera.domain.model.UserRole
import com.example.gestionhotelera.domain.model.ValidationResult
import javax.inject.Inject

class ValidateRoleUseCase @Inject constructor() {
    operator fun invoke(role: UserRole?): ValidationResult {
        if (role == null) {
            return ValidationResult.Error("El rol es obligatorio")
        }
        return ValidationResult.Success
    }
}
