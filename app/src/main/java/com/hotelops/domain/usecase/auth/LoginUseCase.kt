package com.hotelops.domain.usecase.auth

import com.hotelops.domain.model.User
import com.hotelops.domain.repository.AuthRepository
import com.hotelops.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(email: String, password: String): Flow<Resource<User>> {
        return authRepository.login(email, password)
    }
}
