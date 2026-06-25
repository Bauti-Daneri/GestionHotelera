package com.hotelops.domain.usecase.roomservice

import com.hotelops.domain.repository.RoomServiceRepository
import com.hotelops.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeleteOrderUseCase @Inject constructor(
    private val repository: RoomServiceRepository
) {
    operator fun invoke(orderId: String): Flow<Resource<Unit>> {
        return repository.deleteOrder(orderId)
    }
}
