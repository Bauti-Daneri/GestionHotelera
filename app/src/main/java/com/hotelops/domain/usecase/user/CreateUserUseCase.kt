package com.hotelops.domain.usecase.user

import com.hotelops.domain.model.User
import com.hotelops.domain.model.UserRole
import com.hotelops.domain.repository.UserRepository
import com.hotelops.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CreateUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    operator fun invoke(
        hotelId: String,
        name: String,
        email: String,
        password: String,
        role: UserRole,
        department: String,
        phone: String?,
        employeeId: String
    ): Flow<Resource<User>> {
        return userRepository.createUser(
            hotelId = hotelId,
            name = name,
            email = email,
            password = password,
            role = role,
            department = department,
            phone = phone,
            employeeId = employeeId
        )
    }
}
