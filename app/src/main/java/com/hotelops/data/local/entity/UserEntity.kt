package com.hotelops.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hotelops.domain.model.UserRole

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey val id: String,
    val hotelId: String,
    val name: String,
    val email: String,
    val passwordHash: String,
    val role: UserRole,
    val department: String,
    val phone: String?,
    val employeeId: String,
    val createdAt: Long,
    val syncedAt: Long? = null,
    val isDirty: Boolean = false
)
