package com.hotelops.app.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "users",
    foreignKeys = [
        ForeignKey(
            entity = TenantEntity::class,
            parentColumns = ["id"],
            childColumns = ["tenantId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class UserEntity(
    @PrimaryKey
    val id: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val role: String,  // ADMIN, MANAGER, STAFF, GUEST
    val tenantId: String,
    val photoUrl: String?,
    val createdAt: Long,
    val updatedAt: Long,
    val isActive: Boolean = true
)

