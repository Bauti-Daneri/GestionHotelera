package com.hotelops.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hotels")
data class HotelEntity(
    @PrimaryKey val id: String,
    val name: String,
    val address: String,
    val city: String,
    val country: String,
    val phone: String,
    val email: String,
    val createdAt: Long,
    val syncedAt: Long? = null,
    val isDirty: Boolean = false
)
