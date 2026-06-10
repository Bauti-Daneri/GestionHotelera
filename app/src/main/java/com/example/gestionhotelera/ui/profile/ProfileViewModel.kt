package com.example.gestionhotelera.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestionhotelera.domain.usecase.auth.GetActiveUserUseCase
import com.example.gestionhotelera.domain.usecase.profile.GetDarkModeUseCase
import com.example.gestionhotelera.domain.usecase.profile.SetDarkModeUseCase
import com.example.gestionhotelera.domain.usecase.user.UpdateUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getActiveUserUseCase: GetActiveUserUseCase,
    private val getDarkModeUseCase: GetDarkModeUseCase,
    private val setDarkModeUseCase: SetDarkModeUseCase,
    private val updateUserUseCase: UpdateUserUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    init {
        loadProfile()
        observeSettings()
    }

    private fun loadProfile() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(isLoading = true)
            }

            val user = getActiveUserUseCase().firstOrNull()

            _uiState.update {
                it.copy(
                    isLoading = false,
                    user = user,
                    hotelName = "Hotel Plaza Central",
                    hotelId = user?.hotelId ?: "HOTEL-DEMO-001",
                    totalTickets = 12,
                    completedTickets = 8
                )
            }
        }
    }

    private fun observeSettings() {
        getDarkModeUseCase()
            .onEach { isDarkMode ->
                _uiState.update {
                    it.copy(isDarkMode = isDarkMode)
                }
            }
            .launchIn(viewModelScope)
    }

    fun toggleDarkMode(enabled: Boolean) {
        viewModelScope.launch {
            setDarkModeUseCase(enabled)
        }
    }

    fun updateUserPhone(newPhone: String) {
        val currentUser = _uiState.value.user ?: return

        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isLoading = true,
                    error = null,
                    successMessage = null
                )
            }

            val updatedUser = currentUser.copy(phone = newPhone)
            val result = updateUserUseCase(updatedUser)

            result.onSuccess {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        user = updatedUser,
                        successMessage = "Teléfono actualizado correctamente"
                    )
                }
            }.onFailure { error ->
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = error.message ?: "Error desconocido"
                    )
                }
            }
        }
    }

    fun clearMessages() {
        _uiState.update {
            it.copy(error = null, successMessage = null)
        }
    }
}