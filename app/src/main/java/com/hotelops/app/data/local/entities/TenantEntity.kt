package com.hotelops.app.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tenants")
data class TenantEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val email: String,
    val phone: String?,
    val address: String?,
    val city: String?,
    val country: String?,
    val logo: String?,
    val createdAt: Long,
    val updatedAt: Long,
    val isActive: Boolean = true
)

