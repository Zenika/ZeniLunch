package com.zenika.zenilunch.domain

import com.zenika.zenilunch.agency.model.Agency
import com.zenika.zenilunch.repository.AgencyRepository
import javax.inject.Inject

class GetSelectedAgencyUseCase @Inject constructor(
    private val agencyRepository: AgencyRepository
) {
    suspend operator fun invoke(): Agency {
        return agencyRepository.getSelectedAgency()
    }
}
