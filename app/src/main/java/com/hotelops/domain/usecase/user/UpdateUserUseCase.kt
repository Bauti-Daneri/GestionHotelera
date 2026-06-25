package com.hotelops.domain.usecase.user

import com.hotelops.domain.model.User
import com.hotelops.domain.repository.UserRepository
import com.hotelops.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    operator fun invoke(user: User): Flow<Resource<User>> {
        return userRepository.updateUser(user)
    }
}
