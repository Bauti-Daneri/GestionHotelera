package com.example.gestionhotelera.domain.usecase.user

import com.example.gestionhotelera.domain.model.EmployeeShift
import com.example.gestionhotelera.domain.model.ValidationResult
import javax.inject.Inject

class ValidateShiftUseCase @Inject constructor() {
    operator fun invoke(shift: String): ValidationResult {
        if (shift.isBlank()) {
            return ValidationResult.Error("El turno es obligatorio")
        }
        val validShifts = EmployeeShift.entries.map { it.displayName }
        if (!validShifts.contains(shift)) {
            return ValidationResult.Error("Turno no válido")
        }
        return ValidationResult.Success
    }
}
