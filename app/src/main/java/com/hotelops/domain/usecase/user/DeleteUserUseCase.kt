package com.hotelops.domain.usecase.user

import com.hotelops.domain.repository.UserRepository
import com.hotelops.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeleteUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    operator fun invoke(userId: String): Flow<Resource<Unit>> {
        return userRepository.deleteUser(userId)
    }
}
