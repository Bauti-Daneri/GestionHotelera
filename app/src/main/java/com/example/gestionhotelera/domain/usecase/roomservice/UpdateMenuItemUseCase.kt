package com.example.gestionhotelera.domain.usecase.roomservice

import com.example.gestionhotelera.domain.model.RoomServiceMenuItem
import com.example.gestionhotelera.domain.repository.RoomServiceRepository
import javax.inject.Inject

class UpdateMenuItemUseCase @Inject constructor(
    private val repository: RoomServiceRepository
) {
    suspend operator fun invoke(item: RoomServiceMenuItem) = repository.saveMenuItem(item)
}
