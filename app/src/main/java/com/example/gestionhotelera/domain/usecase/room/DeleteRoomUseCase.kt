package com.example.gestionhotelera.domain.usecase.room

import com.example.gestionhotelera.domain.model.Room
import com.example.gestionhotelera.domain.model.TicketStatus
import com.example.gestionhotelera.domain.repository.MaintenanceRepository
import com.example.gestionhotelera.domain.repository.RoomRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class DeleteRoomUseCase @Inject constructor(
    private val repository: RoomRepository,
    private val maintenanceRepository: MaintenanceRepository
) {
    suspend operator fun invoke(room: Room): Result<Unit> {
        // Verificar si la habitación tiene tickets abiertos
        val tickets = maintenanceRepository.getTickets().first()
        val hasOpenTickets = tickets.any { it.roomId == room.id && it.status != TicketStatus.RESOLVED }
        
        if (hasOpenTickets) {
            return Result.failure(Exception("No se puede eliminar una habitación con tickets pendientes de mantenimiento"))
        }

        repository.deleteRoom(room)
        return Result.success(Unit)
    }
}
