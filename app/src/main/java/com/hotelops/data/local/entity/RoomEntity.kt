package com.hotelops.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hotelops.domain.model.RoomStatus
import com.hotelops.domain.model.RoomType

@Entity(tableName = "rooms")
data class RoomEntity(
    @PrimaryKey val id: String,
    val hotelId: String,
    val roomNumber: String,
    val floor: Int,
    val type: RoomType,
    val status: RoomStatus,
    val notes: String?,
    val lastCleanedAt: Long?,
    val lastCleanedBy: String?,
    val createdAt: Long,
    val syncedAt: Long? = null,
    val isDirty: Boolean = false
)
