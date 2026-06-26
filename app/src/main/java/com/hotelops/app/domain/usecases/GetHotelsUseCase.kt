package com.hotelops.app.domain.usecases

import com.hotelops.app.domain.repositories.HotelRepository
import javax.inject.Inject

class GetHotelsUseCase @Inject constructor(
    private val hotelRepository: HotelRepository
) {
    suspend operator fun invoke(tenantId: String) = 
        hotelRepository.getHotelsByTenant(tenantId)
}

