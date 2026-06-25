package com.hotelops.domain.usecase.roomservice

import com.hotelops.domain.model.OrderStatus
import com.hotelops.domain.model.RoomServiceOrder
import com.hotelops.domain.repository.RoomServiceRepository
import com.hotelops.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateOrderStatusUseCase @Inject constructor(
    private val roomServiceRepository: RoomServiceRepository
) {
    operator fun invoke(
        orderId: String,
        status: OrderStatus
    ): Flow<Resource<RoomServiceOrder>> {
        return roomServiceRepository.updateOrderStatus(orderId, status)
    }
}
