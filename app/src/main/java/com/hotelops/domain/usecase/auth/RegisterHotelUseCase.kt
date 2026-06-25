package com.hotelops.domain.usecase.auth

import com.hotelops.domain.model.Hotel
import com.hotelops.domain.model.User
import com.hotelops.domain.repository.AuthRepository
import com.hotelops.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RegisterHotelUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(
        hotelName: String,
        hotelAddress: String,
        hotelCity: String,
        hotelCountry: String,
        hotelPhone: String,
        hotelEmail: String,
        adminName: String,
        adminEmail: String,
        adminPassword: String
    ): Flow<Resource<Pair<Hotel, User>>> {
        return authRepository.registerHotel(
            hotelName = hotelName,
            hotelAddress = hotelAddress,
            hotelCity = hotelCity,
            hotelCountry = hotelCountry,
            hotelPhone = hotelPhone,
            hotelEmail = hotelEmail,
            adminName = adminName,
            adminEmail = adminEmail,
            adminPassword = adminPassword
        )
    }
}
