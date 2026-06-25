package com.hotelops.presentation.login

import com.hotelops.domain.model.User

data class LoginState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val user: User? = null,
    val isLoginSuccessful: Boolean = false
)
