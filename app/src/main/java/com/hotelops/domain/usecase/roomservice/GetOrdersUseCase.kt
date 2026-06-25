package com.hotelops.domain.usecase.roomservice

import com.hotelops.domain.model.RoomServiceOrder
import com.hotelops.domain.repository.RoomServiceRepository
import com.hotelops.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetOrdersUseCase @Inject constructor(
    private val roomServiceRepository: RoomServiceRepository
) {
    operator fun invoke(hotelId: String): Flow<Resource<List<RoomServiceOrder>>> {
        return roomServiceRepository.getOrders(hotelId)
    }
}
