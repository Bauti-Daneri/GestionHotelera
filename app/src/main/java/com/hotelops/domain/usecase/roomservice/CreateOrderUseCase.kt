package com.hotelops.domain.usecase.roomservice

import com.hotelops.domain.model.OrderItem
import com.hotelops.domain.model.RoomServiceOrder
import com.hotelops.domain.repository.RoomServiceRepository
import com.hotelops.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CreateOrderUseCase @Inject constructor(
    private val roomServiceRepository: RoomServiceRepository
) {
    operator fun invoke(
        hotelId: String,
        roomId: String,
        guestName: String,
        items: List<OrderItem>,
        specialInstructions: String?
    ): Flow<Resource<RoomServiceOrder>> {
        return roomServiceRepository.createOrder(
            hotelId = hotelId,
            roomId = roomId,
            guestName = guestName,
            items = items,
            specialInstructions = specialInstructions
        )
    }
}
