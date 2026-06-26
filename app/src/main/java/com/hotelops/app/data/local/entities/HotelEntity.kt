package com.hotelops.app.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "hotels",
    foreignKeys = [
        ForeignKey(
            entity = TenantEntity::class,
            parentColumns = ["id"],
            childColumns = ["tenantId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class HotelEntity(
    @PrimaryKey
    val id: String,
    val tenantId: String,
    val name: String,
    val description: String?,
    val address: String,
    val city: String,
    val country: String,
    val phoneNumber: String?,
    val email: String?,
    val website: String?,
    val rating: Double = 0.0,
    val totalRooms: Int = 0,
    val createdAt: Long,
    val updatedAt: Long,
    val isActive: Boolean = true
)

