package com.example.gestionhotelera.domain.usecase.hotel

import com.example.gestionhotelera.domain.model.Hotel
import com.example.gestionhotelera.domain.repository.HotelRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetHotelUseCase @Inject constructor(
    private val repository: HotelRepository
) {
    operator fun invoke(hotelId: String): Flow<Hotel?> = repository.getHotelById(hotelId)
}
