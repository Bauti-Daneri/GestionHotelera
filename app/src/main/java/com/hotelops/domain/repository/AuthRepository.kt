package com.hotelops.domain.repository

import com.hotelops.domain.model.Hotel
import com.hotelops.domain.model.User
import com.hotelops.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun login(email: String, password: String): Flow<Resource<User>>

    fun registerHotel(
        hotelName: String,
        hotelAddress: String,
        hotelCity: String,
        hotelCountry: String,
        hotelPhone: String,
        hotelEmail: String,
        adminName: String,
        adminEmail: String,
        adminPassword: String
    ): Flow<Resource<Pair<Hotel, User>>>

    fun logout(): Flow<Resource<Unit>>

    fun getCurrentUser(): Flow<User?>
}
