package com.hotelops.app.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "rooms",
    foreignKeys = [
        ForeignKey(
            entity = HotelEntity::class,
            parentColumns = ["id"],
            childColumns = ["hotelId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class RoomEntity(
    @PrimaryKey
    val id: String,
    val hotelId: String,
    val roomNumber: String,
    val roomType: String,  // SINGLE, DOUBLE, SUITE, DELUXE, FAMILY
    val capacity: Int,
    val pricePerNight: Double,
    val description: String?,
    val amenities: String = "",  // JSON serializado
    val isAvailable: Boolean = true,
    val createdAt: Long,
    val updatedAt: Long
)

