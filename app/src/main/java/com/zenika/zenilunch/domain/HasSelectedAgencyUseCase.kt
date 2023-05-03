package com.zenika.zenilunch.domain

import com.zenika.zenilunch.repository.AgencyRepository
import javax.inject.Inject

class HasSelectedAgencyUseCase @Inject constructor(
    private val agencyRepository: AgencyRepository
) {
    suspend operator fun invoke(): Boolean {
        return agencyRepository.hasSelectedAgency()
    }
}
