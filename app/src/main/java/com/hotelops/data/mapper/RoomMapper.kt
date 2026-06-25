package com.hotelops.data.mapper

import com.hotelops.data.local.entity.RoomEntity
import com.hotelops.domain.model.Room

fun RoomEntity.toDomain(): Room {
    return Room(
        id = id,
        hotelId = hotelId,
        roomNumber = roomNumber,
        floor = floor,
        type = type,
        status = status,
        notes = notes,
        lastCleanedAt = lastCleanedAt,
        lastCleanedBy = lastCleanedBy,
        createdAt = createdAt
    )
}

fun Room.toEntity(): RoomEntity {
    return RoomEntity(
        id = id,
        hotelId = hotelId,
        roomNumber = roomNumber,
        floor = floor,
        type = type,
        status = status,
        notes = notes,
        lastCleanedAt = lastCleanedAt,
        lastCleanedBy = lastCleanedBy,
        createdAt = createdAt,
        syncedAt = null,
        isDirty = true
    )
}

fun List<RoomEntity>.toDomain(): List<Room> {
    return map { it.toDomain() }
}

fun List<Room>.toEntity(): List<RoomEntity> {
    return map { it.toEntity() }
}
