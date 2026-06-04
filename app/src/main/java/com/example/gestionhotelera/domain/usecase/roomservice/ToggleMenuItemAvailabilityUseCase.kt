package com.example.gestionhotelera.domain.usecase.roomservice

import com.example.gestionhotelera.domain.repository.RoomServiceRepository
import javax.inject.Inject

class ToggleMenuItemAvailabilityUseCase @Inject constructor(
    private val repository: RoomServiceRepository
) {
    suspend operator fun invoke(id: String) {
        val item = repository.getMenuItemById(id)
        item?.let {
            repository.saveMenuItem(it.copy(isAvailable = !it.isAvailable, updatedAt = System.currentTimeMillis()))
        }
    }
}
