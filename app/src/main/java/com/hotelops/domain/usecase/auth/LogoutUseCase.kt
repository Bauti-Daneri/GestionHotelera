package com.hotelops.domain.usecase.auth

import com.hotelops.domain.repository.AuthRepository
import com.hotelops.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(): Flow<Resource<Unit>> {
        return authRepository.logout()
    }
}
