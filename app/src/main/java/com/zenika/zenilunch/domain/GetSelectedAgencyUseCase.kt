package com.zenika.zenilunch.domain

import com.zenika.zenilunch.ageny.model.Agency
import com.zenika.zenilunch.repository.AgencyRepository
import javax.inject.Inject

class GetSelectedAgencyUseCase @Inject constructor(
    private val agencyRepository: AgencyRepository
) {
    operator fun invoke(): Agency {
        val selectedAgencyName = agencyRepository.getSelectedAgency() ?: "lyon"
        return agencyRepository.getAllAgencies().first { it.id == selectedAgencyName }
    }
}
