package com.hotelops.data.mapper

import com.hotelops.data.local.entity.RoomServiceOrderEntity
import com.hotelops.domain.model.RoomServiceOrder

fun RoomServiceOrderEntity.toDomain(): RoomServiceOrder {
    return RoomServiceOrder(
        id = id,
        hotelId = hotelId,
        roomId = roomId,
        roomNumber = roomNumber,
        guestName = guestName,
        items = items,
        status = status,
        totalAmount = totalAmount,
        specialInstructions = specialInstructions,
        createdAt = createdAt,
        updatedAt = updatedAt,
        deliveredAt = deliveredAt
    )
}

fun RoomServiceOrder.toEntity(): RoomServiceOrderEntity {
    return RoomServiceOrderEntity(
        id = id,
        hotelId = hotelId,
        roomId = roomId,
        roomNumber = roomNumber,
        guestName = guestName,
        items = items,
        status = status,
        totalAmount = totalAmount,
        specialInstructions = specialInstructions,
        createdAt = createdAt,
        updatedAt = updatedAt,
        deliveredAt = deliveredAt,
        syncedAt = null,
        isDirty = true
    )
}

fun List<RoomServiceOrderEntity>.toDomain(): List<RoomServiceOrder> {
    return map { it.toDomain() }
}

fun List<RoomServiceOrder>.toEntity(): List<RoomServiceOrderEntity> {
    return map { it.toEntity() }
}
