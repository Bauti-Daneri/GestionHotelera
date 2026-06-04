package com.example.gestionhotelera.data.repository

import com.example.gestionhotelera.data.local.RoomDao
import com.example.gestionhotelera.data.local.RoomHousekeeperAssignmentDao
import com.example.gestionhotelera.data.local.RoomHousekeeperAssignmentEntity
import com.example.gestionhotelera.data.mapper.toDomain
import com.example.gestionhotelera.data.mapper.toEntity
import com.example.gestionhotelera.domain.model.Room
import com.example.gestionhotelera.domain.model.RoomStatus
import com.example.gestionhotelera.domain.model.SyncStatus
import com.example.gestionhotelera.domain.model.User
import com.example.gestionhotelera.domain.repository.RoomRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RoomRepositoryImpl @Inject constructor(
    private val roomDao: RoomDao,
    private val assignmentDao: RoomHousekeeperAssignmentDao
) : RoomRepository {
    override fun getRooms(): Flow<List<Room>> = roomDao.getAllRooms().map { entities ->
        entities.map { it.toDomain() }
    }

    override suspend fun getRoomById(id: String): Room? = roomDao.getRoomById(id)?.toDomain()

    override suspend fun getRoomByNumber(number: String, hotelId: String): Room? = 
        roomDao.getRoomByNumber(number, hotelId)?.toDomain()

    override suspend fun updateRoomStatus(roomId: String, status: RoomStatus) {
        val room = roomDao.getRoomById(roomId)
        room?.let {
            val updated = it.copy(
                status = status,
                updatedAt = System.currentTimeMillis(),
                isDirty = true,
                syncStatus = SyncStatus.PENDING
            )
            roomDao.updateRoom(updated)
        }
    }

    override suspend fun saveRoom(room: Room) {
        roomDao.insertRoom(room.toEntity(isDirty = true).copy(syncStatus = SyncStatus.PENDING))
    }

    override suspend fun deleteRoom(room: Room) {
        roomDao.deleteRoom(room.toEntity())
    }

    override fun getAssignedHousekeepers(roomId: String): Flow<List<User>> =
        assignmentDao.getHousekeepersForRoom(roomId).map { entities ->
            entities.map { it.toDomain() }
        }

    override fun getRoomsForHousekeeper(userId: String): Flow<List<Room>> =
        assignmentDao.getRoomsForHousekeeper(userId).map { entities ->
            entities.map { it.toDomain() }
        }

    override suspend fun assignHousekeeperToRoom(roomId: String, userId: String, hotelId: String) {
        assignmentDao.insertAssignment(
            RoomHousekeeperAssignmentEntity(
                roomId = roomId,
                userId = userId,
                hotelId = hotelId,
                createdAt = System.currentTimeMillis()
            )
        )
    }

    override suspend fun removeHousekeeperFromRoom(roomId: String, userId: String) {
        assignmentDao.deleteAssignmentByIds(roomId, userId)
    }

    override suspend fun countHousekeepersByRoom(roomId: String): Int =
        assignmentDao.countHousekeepersByRoom(roomId)
}
