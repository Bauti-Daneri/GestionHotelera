package com.hotelops.domain.repository

import com.hotelops.domain.model.User
import com.hotelops.domain.model.UserRole
import com.hotelops.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUsers(hotelId: String): Flow<Resource<List<User>>>

    fun getUserById(userId: String): Flow<Resource<User>>

    fun createUser(
        hotelId: String,
        name: String,
        email: String,
        password: String,
        role: UserRole,
        department: String,
        phone: String?,
        employeeId: String
    ): Flow<Resource<User>>

    fun updateUser(user: User): Flow<Resource<User>>

    fun deleteUser(userId: String): Flow<Resource<Unit>>
}
