package com.hotelops.data.mapper

import com.hotelops.data.local.entity.HotelEntity
import com.hotelops.domain.model.Hotel

fun HotelEntity.toDomain(): Hotel {
    return Hotel(
        id = id,
        name = name,
        address = address,
        city = city,
        country = country,
        phone = phone,
        email = email,
        createdAt = createdAt
    )
}

fun Hotel.toEntity(): HotelEntity {
    return HotelEntity(
        id = id,
        name = name,
        address = address,
        city = city,
        country = country,
        phone = phone,
        email = email,
        createdAt = createdAt,
        syncedAt = null,
        isDirty = true
    )
}
