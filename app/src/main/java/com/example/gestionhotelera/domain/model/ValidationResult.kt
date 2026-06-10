package com.example.gestionhotelera.domain.model

sealed class ValidationResult {
    object Success : ValidationResult()
    data class Error(val message: String) : ValidationResult()
}

data class EmployeeFormResult(
    val nameError: String? = null,
    val emailError: String? = null,
    val phoneError: String? = null,
    val passwordError: String? = null,
    val roleError: String? = null,
    val shiftError: String? = null,
    val isValid: Boolean = false
)
