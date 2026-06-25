package com.hotelops.presentation.register

sealed class RegisterHotelEvent {
    data class HotelNameChanged(val value: String) : RegisterHotelEvent()
    data class HotelAddressChanged(val value: String) : RegisterHotelEvent()
    data class HotelCityChanged(val value: String) : RegisterHotelEvent()
    data class HotelCountryChanged(val value: String) : RegisterHotelEvent()
    data class HotelPhoneChanged(val value: String) : RegisterHotelEvent()
    data class HotelEmailChanged(val value: String) : RegisterHotelEvent()
    data class AdminNameChanged(val value: String) : RegisterHotelEvent()
    data class AdminEmailChanged(val value: String) : RegisterHotelEvent()
    data class AdminPasswordChanged(val value: String) : RegisterHotelEvent()
    data class AdminPasswordConfirmChanged(val value: String) : RegisterHotelEvent()
    object Register : RegisterHotelEvent()
}
