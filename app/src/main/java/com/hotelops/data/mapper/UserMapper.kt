package com.hotelops.data.mapper

import com.hotelops.data.local.entity.UserEntity
import com.hotelops.domain.model.User

fun UserEntity.toDomain(): User {
    return User(
        id = id,
        hotelId = hotelId,
        name = name,
        email = email,
        role = role,
        department = department,
        phone = phone,
        employeeId = employeeId,
        createdAt = createdAt
    )
}

fun User.toEntity(passwordHash: String = ""): UserEntity {
    return UserEntity(
        id = id,
        hotelId = hotelId,
        name = name,
        email = email,
        passwordHash = passwordHash,
        role = role,
        department = department,
        phone = phone,
        employeeId = employeeId,
        createdAt = createdAt,
        syncedAt = null,
        isDirty = true
    )
}

fun List<UserEntity>.toDomain(): List<User> {
    return map { it.toDomain() }
}

fun List<User>.toEntity(): List<UserEntity> {
    return map { it.toEntity() }
}
