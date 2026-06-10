package com.example.gestionhotelera.domain.usecase.user

import com.example.gestionhotelera.domain.model.User
import com.example.gestionhotelera.domain.repository.UserRepository
import javax.inject.Inject

class UpdateUserUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(user: User): Result<Unit> = runCatching { 
        repository.saveUser(user)
    }
}
