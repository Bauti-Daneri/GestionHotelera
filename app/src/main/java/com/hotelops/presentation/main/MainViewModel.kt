package com.hotelops.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hotelops.domain.model.User
import com.hotelops.domain.usecase.auth.GetCurrentUserUseCase
import com.hotelops.domain.usecase.auth.LogoutUseCase
import com.hotelops.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

data class MainState(
    val currentUser: User? = null,
    val isLoggedOut: Boolean = false,
    val isReady: Boolean = false
)

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(MainState())
    val state: StateFlow<MainState> = _state.asStateFlow()

    init {
        observeCurrentUser()
    }

    private fun observeCurrentUser() {
        viewModelScope.launch {
            getCurrentUserUseCase().collect { user ->
                _state.value = _state.value.copy(currentUser = user, isReady = true)
            }
        }
    }

    fun logout() {
        logoutUseCase().onEach { result ->
            if (result is Resource.Success) {
                _state.value = _state.value.copy(isLoggedOut = true)
            }
        }.launchIn(viewModelScope)
    }
}
