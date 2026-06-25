package com.hotelops.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hotelops.domain.usecase.auth.LoginUseCase
import com.hotelops.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState> = _state.asStateFlow()

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.EmailChanged -> {
                _state.update { it.copy(email = event.email) }
            }
            is LoginEvent.PasswordChanged -> {
                _state.update { it.copy(password = event.password) }
            }
            is LoginEvent.Login -> {
                login()
            }
            is LoginEvent.NavigateToRegister -> {
                // Navigation handled by UI
            }
            is LoginEvent.ClearError -> {
                _state.update { it.copy(error = null) }
            }
        }
    }

    private fun login() {
        val email = _state.value.email
        val password = _state.value.password

        if (email.isBlank() || password.isBlank()) {
            _state.update { it.copy(error = "Email and password are required") }
            return
        }

        viewModelScope.launch {
            loginUseCase(email, password).collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.update { it.copy(isLoading = true, error = null) }
                    }
                    is Resource.Success -> {
                        _state.update {
                            it.copy(
                                isLoading = false,
                                user = result.data,
                                isLoginSuccessful = true,
                                error = null
                            )
                        }
                    }
                    is Resource.Error -> {
                        _state.update {
                            it.copy(
                                isLoading = false,
                                error = result.message ?: "Login failed"
                            )
                        }
                    }
                }
            }
        }
    }
}
