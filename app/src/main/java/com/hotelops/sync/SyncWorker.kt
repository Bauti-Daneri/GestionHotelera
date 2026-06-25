package com.hotelops.sync

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.firebase.firestore.FirebaseFirestore
import com.hotelops.data.local.dao.*
import com.hotelops.domain.model.*
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.tasks.await
import timber.log.Timber

@HiltWorker
class SyncWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val firestore: FirebaseFirestore,
    private val hotelDao: HotelDao,
    private val userDao: UserDao,
    private val roomDao: RoomDao,
    private val maintenanceDao: MaintenanceDao,
    private val roomServiceDao: RoomServiceDao
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        Timber.d("Starting SyncWorker...")
        return try {
            syncHotels()
            syncUsers()
            syncRooms()
            syncMaintenanceTickets()
            syncRoomServiceOrders()
            Result.success()
        } catch (e: Exception) {
            Timber.e(e, "SyncWorker failed")
            Result.retry()
        }
    }

    private suspend fun syncHotels() {
        val dirtyHotels = hotelDao.getDirtyHotels()
        dirtyHotels.forEach { hotel ->
            val data = mapOf(
                "id" to hotel.id,
                "name" to hotel.name,
                "address" to hotel.address,
                "city" to hotel.city,
                "country" to hotel.country,
                "phone" to hotel.phone,
                "email" to hotel.email,
                "createdAt" to hotel.createdAt
            )
            firestore.collection("hotels").document(hotel.id).set(data).await()
            hotelDao.markAsSynced(hotel.id, System.currentTimeMillis())
        }
    }

    private suspend fun syncUsers() {
        val dirtyUsers = userDao.getDirtyUsers()
        dirtyUsers.forEach { user ->
            val data = mapOf(
                "id" to user.id,
                "hotelId" to user.hotelId,
                "name" to user.name,
                "email" to user.email,
                "role" to user.role.name,
                "department" to user.department,
                "phone" to (user.phone ?: ""),
                "employeeId" to user.employeeId,
                "createdAt" to user.createdAt
            )
            firestore.collection("users").document(user.id).set(data).await()
            userDao.markAsSynced(user.id, System.currentTimeMillis())
        }
    }

    private suspend fun syncRooms() {
        val dirtyRooms = roomDao.getDirtyRooms()
        dirtyRooms.forEach { room ->
            val data = mapOf(
                "id" to room.id,
                "hotelId" to room.hotelId,
                "roomNumber" to room.roomNumber,
                "floor" to room.floor,
                "type" to room.type.name,
                "status" to room.status.name,
                "notes" to (room.notes ?: ""),
                "lastCleanedAt" to (room.lastCleanedAt ?: 0L),
                "lastCleanedBy" to (room.lastCleanedBy ?: ""),
                "createdAt" to room.createdAt
            )
            firestore.collection("rooms").document(room.id).set(data).await()
            roomDao.markAsSynced(room.id, System.currentTimeMillis())
        }
    }

    private suspend fun syncMaintenanceTickets() {
        val dirtyTickets = maintenanceDao.getDirtyTickets()
        dirtyTickets.forEach { ticket ->
            val data = mapOf(
                "id" to ticket.id,
                "hotelId" to ticket.hotelId,
                "roomId" to ticket.roomId,
                "roomNumber" to ticket.roomNumber,
                "title" to ticket.title,
                "description" to ticket.description,
                "category" to ticket.category.name,
                "status" to ticket.status.name,
                "priority" to ticket.priority,
                "reportedBy" to ticket.reportedBy,
                "imageUrl" to (ticket.imageUrl ?: ""),
                "createdAt" to ticket.createdAt,
                "updatedAt" to ticket.updatedAt,
                "completedAt" to (ticket.completedAt ?: 0L)
            )
            firestore.collection("maintenance_tickets").document(ticket.id).set(data).await()
            maintenanceDao.markAsSynced(ticket.id, System.currentTimeMillis())
        }
    }

    private suspend fun syncRoomServiceOrders() {
        val dirtyOrders = roomServiceDao.getDirtyOrders()
        dirtyOrders.forEach { order ->
            val itemsData = order.items.map { 
                mapOf("name" to it.name, "quantity" to it.quantity, "price" to it.price) 
            }
            val data = mapOf(
                "id" to order.id,
                "hotelId" to order.hotelId,
                "roomId" to order.roomId,
                "roomNumber" to order.roomNumber,
                "guestName" to order.guestName,
                "items" to itemsData,
                "status" to order.status.name,
                "totalAmount" to order.totalAmount,
                "specialInstructions" to (order.specialInstructions ?: ""),
                "createdAt" to order.createdAt,
                "updatedAt" to order.updatedAt,
                "deliveredAt" to (order.deliveredAt ?: 0L)
            )
            firestore.collection("room_service_orders").document(order.id).set(data).await()
            roomServiceDao.markAsSynced(order.id, System.currentTimeMillis())
        }
    }
}
