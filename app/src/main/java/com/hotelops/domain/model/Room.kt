package com.hotelops.domain.model

enum class RoomStatus {
    DIRTY,
    CLEANING,
    CLEAN,
    INSPECTING,
    OUT_OF_SERVICE
}

enum class RoomType {
    SINGLE,
    DOUBLE,
    SUITE
}

data class Room(
    val id: String,
    val hotelId: String,
    val roomNumber: String,
    val floor: Int,
    val type: RoomType,
    val status: RoomStatus,
    val notes: String?,
    val lastCleanedAt: Long?,
    val lastCleanedBy: String?,
    val createdAt: Long
)
