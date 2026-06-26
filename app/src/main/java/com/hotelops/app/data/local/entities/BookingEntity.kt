package com.hotelops.app.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "bookings",
    foreignKeys = [
        ForeignKey(
            entity = HotelEntity::class,
            parentColumns = ["id"],
            childColumns = ["hotelId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = RoomEntity::class,
            parentColumns = ["id"],
            childColumns = ["roomId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["id"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class BookingEntity(
    @PrimaryKey
    val id: String,
    val hotelId: String,
    val roomId: String,
    val userId: String,
    val tenantId: String,
    val checkInDate: Long,  // Milisegundos desde epoch
    val checkOutDate: Long,
    val guestName: String,
    val guestEmail: String,
    val guestPhone: String,
    val totalPrice: Double,
    val status: String,  // PENDING, CONFIRMED, CHECKED_IN, CHECKED_OUT, CANCELLED, NO_SHOW
    val specialRequests: String? = null,
    val createdAt: Long,
    val updatedAt: Long
)

