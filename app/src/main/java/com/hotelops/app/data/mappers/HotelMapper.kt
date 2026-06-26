package com.hotelops.app.data.mappers

import com.hotelops.app.data.local.entities.HotelEntity
import com.hotelops.app.data.remote.dto.HotelDto
import com.hotelops.app.domain.models.Hotel
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

/**
 * Mapper para Hotel (Entity ↔ DTO ↔ Domain)
 */
object HotelMapper {
    private val formatter = DateTimeFormatter.ISO_INSTANT

    fun dtoToDomain(dto: HotelDto): Hotel {
        return Hotel(
            id = dto.id,
            tenantId = dto.tenantId,
            name = dto.name,
            description = dto.description,
            address = dto.address,
            city = dto.city,
            country = dto.country,
            phoneNumber = dto.phoneNumber,
            email = dto.email,
            website = dto.website,
            rating = dto.rating,
            totalRooms = dto.totalRooms,
            createdAt = parseDateTime(dto.createdAt),
            updatedAt = parseDateTime(dto.updatedAt),
            isActive = dto.isActive
        )
    }

    fun entityToDomain(entity: HotelEntity): Hotel {
        return Hotel(
            id = entity.id,
            tenantId = entity.tenantId,
            name = entity.name,
            description = entity.description,
            address = entity.address,
            city = entity.city,
            country = entity.country,
            phoneNumber = entity.phoneNumber,
            email = entity.email,
            website = entity.website,
            rating = entity.rating,
            totalRooms = entity.totalRooms,
            createdAt = Instant.ofEpochMilli(entity.createdAt)
                .atZone(ZoneId.systemDefault()).toLocalDateTime(),
            updatedAt = Instant.ofEpochMilli(entity.updatedAt)
                .atZone(ZoneId.systemDefault()).toLocalDateTime(),
            isActive = entity.isActive
        )
    }

    fun domainToEntity(domain: Hotel): HotelEntity {
        return HotelEntity(
            id = domain.id,
            tenantId = domain.tenantId,
            name = domain.name,
            description = domain.description,
            address = domain.address,
            city = domain.city,
            country = domain.country,
            phoneNumber = domain.phoneNumber,
            email = domain.email,
            website = domain.website,
            rating = domain.rating,
            totalRooms = domain.totalRooms,
            createdAt = domain.createdAt.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli(),
            updatedAt = domain.updatedAt.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli(),
            isActive = domain.isActive
        )
    }

    fun dtoToEntity(dto: HotelDto): HotelEntity {
        return HotelEntity(
            id = dto.id,
            tenantId = dto.tenantId,
            name = dto.name,
            description = dto.description,
            address = dto.address,
            city = dto.city,
            country = dto.country,
            phoneNumber = dto.phoneNumber,
            email = dto.email,
            website = dto.website,
            rating = dto.rating,
            totalRooms = dto.totalRooms,
            createdAt = parseDateTime(dto.createdAt).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli(),
            updatedAt = parseDateTime(dto.updatedAt).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli(),
            isActive = dto.isActive
        )
    }

    private fun parseDateTime(dateString: String): LocalDateTime {
        return try {
            LocalDateTime.parse(dateString, formatter)
        } catch (e: Exception) {
            LocalDateTime.now()
        }
    }
}

