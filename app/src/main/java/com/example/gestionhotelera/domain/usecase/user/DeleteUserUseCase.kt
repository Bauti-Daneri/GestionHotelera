package com.example.gestionhotelera.domain.usecase.user

import com.example.gestionhotelera.domain.model.User
import com.example.gestionhotelera.domain.repository.UserRepository
import javax.inject.Inject

class DeleteUserUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(user: User) = repository.deleteUser(user)
}
