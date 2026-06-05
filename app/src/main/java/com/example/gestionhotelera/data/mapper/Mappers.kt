package com.example.gestionhotelera.data.mapper

import com.example.gestionhotelera.data.local.*
import com.example.gestionhotelera.domain.model.*

fun HotelEntity.toDomain() = Hotel(
    id = id,
    name = name,
    address = address,
    phone = phone,
    createdAt = createdAt,
    updatedAt = updatedAt,
    syncStatus = syncStatus
)

fun Hotel.toEntity(isDirty: Boolean = false) = HotelEntity(
    id = id,
    name = name,
    address = address,
    phone = phone,
    createdAt = createdAt,
    updatedAt = updatedAt,
    isDirty = isDirty,
    syncStatus = syncStatus
)

fun UserEntity.toDomain() = User(
    id = id,
    hotelId = hotelId,
    name = name,
    email = email,
    role = role,
    phone = phone,
    department = department,
    createdAt = createdAt,
    updatedAt = updatedAt,
    syncStatus = syncStatus
)

fun User.toEntity(isDirty: Boolean = false) = UserEntity(
    id = id,
    hotelId = hotelId,
    name = name,
    email = email,
    role = role,
    phone = phone,
    department = department,
    createdAt = createdAt,
    updatedAt = updatedAt,
    isDirty = isDirty,
    syncStatus = syncStatus
)

fun RoomEntity.toDomain() = Room(
    id = id,
    hotelId = hotelId,
    number = number,
    type = type,
    status = status,
    lastCleaned = lastCleaned,
    createdAt = createdAt,
    updatedAt = updatedAt,
    syncStatus = syncStatus
)

fun Room.toEntity(isDirty: Boolean = false) = RoomEntity(
    id = id,
    hotelId = hotelId,
    number = number,
    type = type,
    status = status,
    lastCleaned = lastCleaned,
    createdAt = createdAt,
    updatedAt = updatedAt,
    isDirty = isDirty,
    syncStatus = syncStatus
)

fun MaintenanceTicketEntity.toDomain() = MaintenanceTicket(
    id = id,
    hotelId = hotelId,
    roomId = roomId,
    description = description,
    category = category,
    status = status,
    reportedBy = reportedBy,
    imageUrl = imageUrl,
    createdAt = createdAt,
    updatedAt = updatedAt,
    syncStatus = syncStatus
)

fun MaintenanceTicket.toEntity(isDirty: Boolean = false) = MaintenanceTicketEntity(
    id = id,
    hotelId = hotelId,
    roomId = roomId,
    description = description,
    category = category,
    status = status,
    reportedBy = reportedBy,
    imageUrl = imageUrl,
    createdAt = createdAt,
    updatedAt = updatedAt,
    isDirty = isDirty,
    syncStatus = syncStatus
)

fun RoomServiceOrderEntity.toDomain() = RoomServiceOrder(
    id = id,
    hotelId = hotelId,
    roomId = roomId,
    description = description,
    status = status,
    price = price,
    createdAt = createdAt,
    updatedAt = updatedAt,
    syncStatus = syncStatus
)

fun RoomServiceOrder.toEntity(isDirty: Boolean = false) = RoomServiceOrderEntity(
    id = id,
    hotelId = hotelId,
    roomId = roomId,
    description = description,
    status = status,
    price = price,
    createdAt = createdAt,
    updatedAt = updatedAt,
    isDirty = isDirty,
    syncStatus = syncStatus
)

fun RoomHousekeeperAssignmentEntity.toDomain() = RoomHousekeeperAssignment(
    roomId = roomId,
    userId = userId,
    hotelId = hotelId,
    createdAt = createdAt
)

fun RoomHousekeeperAssignment.toEntity() = RoomHousekeeperAssignmentEntity(
    roomId = roomId,
    userId = userId,
    hotelId = hotelId,
    createdAt = createdAt
)
