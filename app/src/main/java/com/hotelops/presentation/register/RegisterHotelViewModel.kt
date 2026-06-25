package com.hotelops.presentation.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hotelops.domain.usecase.auth.RegisterHotelUseCase
import com.hotelops.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RegisterHotelViewModel @Inject constructor(
    private val registerHotelUseCase: RegisterHotelUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(RegisterHotelState())
    val state: StateFlow<RegisterHotelState> = _state.asStateFlow()

    fun onEvent(event: RegisterHotelEvent) {
        when (event) {
            is RegisterHotelEvent.HotelNameChanged -> _state.value = _state.value.copy(hotelName = event.value)
            is RegisterHotelEvent.HotelAddressChanged -> _state.value = _state.value.copy(hotelAddress = event.value)
            is RegisterHotelEvent.HotelCityChanged -> _state.value = _state.value.copy(hotelCity = event.value)
            is RegisterHotelEvent.HotelCountryChanged -> _state.value = _state.value.copy(hotelCountry = event.value)
            is RegisterHotelEvent.HotelPhoneChanged -> _state.value = _state.value.copy(hotelPhone = event.value)
            is RegisterHotelEvent.HotelEmailChanged -> _state.value = _state.value.copy(hotelEmail = event.value)
            is RegisterHotelEvent.AdminNameChanged -> _state.value = _state.value.copy(adminName = event.value)
            is RegisterHotelEvent.AdminEmailChanged -> _state.value = _state.value.copy(adminEmail = event.value)
            is RegisterHotelEvent.AdminPasswordChanged -> _state.value = _state.value.copy(adminPassword = event.value)
            is RegisterHotelEvent.AdminPasswordConfirmChanged -> _state.value = _state.value.copy(adminPasswordConfirm = event.value)
            is RegisterHotelEvent.Register -> register()
        }
    }

    private fun register() {
        val s = _state.value
        if (s.hotelName.isBlank() || s.adminEmail.isBlank() || s.adminPassword.isBlank()) {
            _state.value = s.copy(error = "Por favor completa todos los campos obligatorios")
            return
        }
        if (s.adminPassword != s.adminPasswordConfirm) {
            _state.value = s.copy(error = "Las contraseñas no coinciden")
            return
        }
        if (s.adminPassword.length < 6) {
            _state.value = s.copy(error = "La contraseña debe tener al menos 6 caracteres")
            return
        }

        registerHotelUseCase(
            hotelName = s.hotelName,
            hotelAddress = s.hotelAddress,
            hotelCity = s.hotelCity,
            hotelCountry = s.hotelCountry,
            hotelPhone = s.hotelPhone,
            hotelEmail = s.hotelEmail,
            adminName = s.adminName,
            adminEmail = s.adminEmail,
            adminPassword = s.adminPassword
        ).onEach { result ->
            when (result) {
                is Resource.Loading -> _state.value = _state.value.copy(isLoading = true, error = null)
                is Resource.Success -> _state.value = _state.value.copy(isLoading = false, isSuccess = true)
                is Resource.Error -> _state.value = _state.value.copy(isLoading = false, error = result.message)
            }
        }.launchIn(viewModelScope)
    }
}
