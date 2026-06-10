package com.example.gestionhotelera.domain.usecase.user

import com.example.gestionhotelera.domain.model.EmployeeFormResult
import com.example.gestionhotelera.domain.model.UserRole
import com.example.gestionhotelera.domain.model.ValidationResult
import javax.inject.Inject

class ValidateEmployeeFormUseCase @Inject constructor(
    private val validateName: ValidateEmployeeNameUseCase,
    private val validateEmail: ValidateEmailUseCase,
    private val validatePhone: ValidatePhoneUseCase,
    private val validatePassword: ValidatePasswordUseCase,
    private val validateRole: ValidateRoleUseCase,
    private val validateShift: ValidateShiftUseCase
) {
    operator fun invoke(
        name: String,
        email: String,
        phone: String,
        password: String,
        role: UserRole?,
        shift: String,
        isNewUser: Boolean = true
    ): EmployeeFormResult {
        val nameRes = validateName(name)
        val emailRes = validateEmail(email)
        val phoneRes = validatePhone(phone)
        val passwordRes = validatePassword(password, isRequired = isNewUser)
        val roleRes = validateRole(role)
        val shiftRes = validateShift(shift)

        val hasError = listOf(nameRes, emailRes, phoneRes, passwordRes, roleRes, shiftRes)
            .any { it is ValidationResult.Error }

        return EmployeeFormResult(
            nameError = (nameRes as? ValidationResult.Error)?.message,
            emailError = (emailRes as? ValidationResult.Error)?.message,
            phoneError = (phoneRes as? ValidationResult.Error)?.message,
            passwordError = (passwordRes as? ValidationResult.Error)?.message,
            roleError = (roleRes as? ValidationResult.Error)?.message,
            shiftError = (shiftRes as? ValidationResult.Error)?.message,
            isValid = !hasError
        )
    }
}
