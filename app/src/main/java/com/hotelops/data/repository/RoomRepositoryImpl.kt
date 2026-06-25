package com.hotelops.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.hotelops.data.local.dao.RoomDao
import com.hotelops.data.local.entity.RoomEntity
import com.hotelops.data.mapper.toDomain
import com.hotelops.domain.model.Room
import com.hotelops.domain.model.RoomStatus
import com.hotelops.domain.model.RoomType
import com.hotelops.domain.repository.RoomRepository
import com.hotelops.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import java.util.UUID
import javax.inject.Inject

class RoomRepositoryImpl @Inject constructor(
    private val roomDao: RoomDao,
    private val firestore: FirebaseFirestore
) : RoomRepository {

    companion object {
        private const val COL_ROOMS = "rooms"
    }

    override fun getRooms(hotelId: String): Flow<Resource<List<Room>>> {
        return roomDao.getRoomsByHotel(hotelId).map { entities ->
            Resource.Success(entities.toDomain())
        }
    }

    override fun getRoomById(roomId: String): Flow<Resource<Room>> {
        return roomDao.getRoomById(roomId).map { entity ->
            if (entity != null) Resource.Success(entity.toDomain())
            else Resource.Error("Habitación no encontrada")
        }
    }

    override fun updateRoomStatus(
        roomId: String,
        status: RoomStatus,
        cleanedBy: String?
    ): Flow<Resource<Room>> = flow {
        emit(Resource.Loading())
        try {
            val now = System.currentTimeMillis()
            val cleanedAt = if (status == RoomStatus.CLEAN) now else null

            // 1. Actualizar Room local
            roomDao.updateRoomStatus(roomId, status, cleanedAt, cleanedBy)

            // 2. Sincronizar con Firestore
            val update = mutableMapOf<String, Any>(
                "status" to status.name,
                "updatedAt" to now
            )
            cleanedAt?.let { update["lastCleanedAt"] = it }
            cleanedBy?.let { update["lastCleanedBy"] = it }

            firestore.collection(COL_ROOMS).document(roomId).update(update).await()

            val updated = roomDao.getRoomByNumber("", "")
            val entity = roomDao.getRoomById(roomId).first()
            if (entity != null) emit(Resource.Success(entity.toDomain()))
        } catch (e: Exception) {
            val entity = roomDao.getRoomById(roomId).first()
            if (entity != null) emit(Resource.Success(entity.toDomain()))
            else emit(Resource.Error(e.message ?: "Error al actualizar habitación"))
        }
    }

    override fun addRoom(
        hotelId: String,
        roomNumber: String,
        floor: Int,
        type: RoomType
    ): Flow<Resource<Room>> = flow {
        emit(Resource.Loading())
        try {
            val roomId = UUID.randomUUID().toString()
            val now = System.currentTimeMillis()

            val entity = RoomEntity(
                id = roomId,
                hotelId = hotelId,
                roomNumber = roomNumber,
                floor = floor,
                type = type,
                status = RoomStatus.CLEAN,
                notes = null,
                lastCleanedAt = null,
                lastCleanedBy = null,
                createdAt = now,
                isDirty = true
            )

            // 1. Guardar local primero (offline-first)
            roomDao.insertRoom(entity)

            // 2. Sincronizar con Firestore
            val data = mapOf(
                "id" to roomId,
                "hotelId" to hotelId,
                "roomNumber" to roomNumber,
                "floor" to floor,
                "type" to type.name,
                "status" to RoomStatus.CLEAN.name,
                "createdAt" to now
            )
            firestore.collection(COL_ROOMS).document(roomId).set(data).await()
            roomDao.markAsSynced(roomId, System.currentTimeMillis())

            emit(Resource.Success(entity.toDomain()))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Error al agregar habitación"))
        }
    }

    override fun deleteRoom(roomId: String): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading())
        try {
            roomDao.deleteRoomById(roomId)
            firestore.collection(COL_ROOMS).document(roomId).delete().await()
            emit(Resource.Success(Unit))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Error al eliminar habitación"))
        }
    }
}
