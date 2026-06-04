package com.example.gestionhotelera.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestionhotelera.domain.repository.UserRepository
import com.example.gestionhotelera.domain.model.CURRENT_DEMO_ROLE
import com.example.gestionhotelera.domain.model.DemoRole
import com.example.gestionhotelera.domain.repository.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val profileRepository: ProfileRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    init {
        loadProfile()
        observeSettings()
    }

    private fun loadProfile() {
        viewModelScope.launch {
            val role = CURRENT_DEMO_ROLE ?: return@launch
            
            _uiState.update { it.copy(isLoading = true) }
            // In a real app we would get the current user ID from session
            val demoUserId = when (role) {
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

    private fun observeSettings() {
        profileRepository.isDarkMode
            .onEach { isDarkMode ->
                _uiState.update { it.copy(isDarkMode = isDarkMode) }
            }
            .launchIn(viewModelScope)
    }

    fun toggleDarkMode(enabled: Boolean) {
        viewModelScope.launch {
            profileRepository.setDarkMode(enabled)
        }
    }
}
