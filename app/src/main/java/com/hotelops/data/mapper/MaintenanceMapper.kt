package com.hotelops.data.mapper

import com.hotelops.data.local.entity.MaintenanceTicketEntity
import com.hotelops.domain.model.MaintenanceTicket

fun MaintenanceTicketEntity.toDomain(): MaintenanceTicket {
    return MaintenanceTicket(
        id = id,
        hotelId = hotelId,
        roomId = roomId,
        roomNumber = roomNumber,
        title = title,
        description = description,
        category = category,
        status = status,
        priority = priority,
        reportedBy = reportedBy,
        reportedByName = reportedByName,
        assignedTo = assignedTo,
        assignedToName = assignedToName,
        imageUrl = imageUrl,
        createdAt = createdAt,
        updatedAt = updatedAt,
        completedAt = completedAt
    )
}

fun MaintenanceTicket.toEntity(): MaintenanceTicketEntity {
    return MaintenanceTicketEntity(
        id = id,
        hotelId = hotelId,
        roomId = roomId,
        roomNumber = roomNumber,
        title = title,
        description = description,
        category = category,
        status = status,
        priority = priority,
        reportedBy = reportedBy,
        reportedByName = reportedByName,
        assignedTo = assignedTo,
        assignedToName = assignedToName,
        imageUrl = imageUrl,
        createdAt = createdAt,
        updatedAt = updatedAt,
        completedAt = completedAt,
        syncedAt = null,
        isDirty = true
    )
}

fun List<MaintenanceTicketEntity>.toDomain(): List<MaintenanceTicket> {
    return map { it.toDomain() }
}

fun List<MaintenanceTicket>.toEntity(): List<MaintenanceTicketEntity> {
    return map { it.toEntity() }
}
