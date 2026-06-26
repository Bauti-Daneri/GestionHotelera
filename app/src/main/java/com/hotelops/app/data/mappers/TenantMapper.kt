package com.hotelops.app.data.mappers

import com.hotelops.app.data.local.entities.TenantEntity
import com.hotelops.app.domain.models.Tenant
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

/**
 * Mapper para convertir entre TenantEntity (local) y Tenant (domain)
 * No tiene responsabilidades de UI ni negocio
 */
object TenantMapper {
    fun entityToDomain(entity: TenantEntity): Tenant {
        return Tenant(
            id = entity.id,
            name = entity.name,
            email = entity.email,
            phone = entity.phone,
            address = entity.address,
            city = entity.city,
            country = entity.country,
            logo = entity.logo,
            createdAt = Instant.ofEpochMilli(entity.createdAt)
                .atZone(ZoneId.systemDefault()).toLocalDateTime(),
            updatedAt = Instant.ofEpochMilli(entity.updatedAt)
                .atZone(ZoneId.systemDefault()).toLocalDateTime(),
            isActive = entity.isActive
        )
    }

    fun domainToEntity(domain: Tenant): TenantEntity {
        return TenantEntity(
            id = domain.id,
            name = domain.name,
            email = domain.email,
            phone = domain.phone,
            address = domain.address,
            city = domain.city,
            country = domain.country,
            logo = domain.logo,
            createdAt = domain.createdAt
                .atZone(ZoneId.systemDefault()).toInstant().toEpochMilli(),
            updatedAt = domain.updatedAt
                .atZone(ZoneId.systemDefault()).toInstant().toEpochMilli(),
            isActive = domain.isActive
        )
    }
}

