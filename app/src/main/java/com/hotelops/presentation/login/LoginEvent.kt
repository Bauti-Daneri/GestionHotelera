package com.hotelops.presentation.login

sealed class LoginEvent {
    data class EmailChanged(val email: String) : LoginEvent()
    data class PasswordChanged(val password: String) : LoginEvent()
    object Login : LoginEvent()
    object NavigateToRegister : LoginEvent()
    object ClearError : LoginEvent()
}
