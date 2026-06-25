package com.hotelops.presentation.register

data class RegisterHotelState(
    val hotelName: String = "",
    val hotelAddress: String = "",
    val hotelCity: String = "",
    val hotelCountry: String = "",
    val hotelPhone: String = "",
    val hotelEmail: String = "",
    val adminName: String = "",
    val adminEmail: String = "",
    val adminPassword: String = "",
    val adminPasswordConfirm: String = "",
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val error: String? = null
)
