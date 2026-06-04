package com.example.gestionhotelera.domain.usecase.roomservice

import com.example.gestionhotelera.domain.model.RoomServiceMenuItem
import com.example.gestionhotelera.domain.repository.RoomServiceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMenuItemsUseCase @Inject constructor(
    private val repository: RoomServiceRepository
) {
    operator fun invoke(onlyAvailable: Boolean = false): Flow<List<RoomServiceMenuItem>> {
        return if (onlyAvailable) {
            repository.getAvailableMenuItems()
        } else {
            repository.getMenuItems()
        }
    }
}
