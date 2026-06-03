package com.example.gestionhotelera.domain.usecase.user

import com.example.gestionhotelera.domain.model.User
import com.example.gestionhotelera.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val repository: UserRepository
) {
    operator fun invoke(): Flow<List<User>> = repository.getUsers()
}
