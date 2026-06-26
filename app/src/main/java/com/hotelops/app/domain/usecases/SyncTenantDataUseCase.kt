package com.hotelops.app.domain.usecases

import com.hotelops.app.domain.repositories.TenantRepository
import javax.inject.Inject

class SyncTenantDataUseCase @Inject constructor(
    private val tenantRepository: TenantRepository
) {
    suspend operator fun invoke() = tenantRepository.syncTenantData()
}

