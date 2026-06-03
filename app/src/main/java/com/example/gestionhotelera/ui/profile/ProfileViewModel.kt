package com.example.gestionhotelera.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestionhotelera.domain.repository.UserRepository
import com.example.gestionhotelera.domain.model.CURRENT_DEMO_ROLE
import com.example.gestionhotelera.domain.model.DemoRole
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    init {
        loadProfile()
    }

    private fun loadProfile() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            // In a real app we would get the current user ID from session
            val demoUserId = when (CURRENT_DEMO_ROLE) {
                DemoRole.ADMIN -> "admin-01"
                DemoRole.HOUSEKEEPING -> "house-01"
                DemoRole.MAINTENANCE -> "maint-01"
            }
            
            val user = userRepository.getUserById(demoUserId)
            _uiState.update { 
                it.copy(
                    isLoading = false,
                    user = user,
                    hotelName = "Hotel Plaza Central",
                    hotelId = "HOTEL-DEMO-001"
                )
            }
        }
    }

    fun toggleDarkMode(enabled: Boolean) {
        _uiState.update { it.copy(isDarkMode = enabled) }
    }
}
