package com.zenika.zenilunch.domain

import com.zenika.zenilunch.ageny.model.Agency
import com.zenika.zenilunch.repository.AgencyRepository
import javax.inject.Inject

class GetAllAgenciesUseCase @Inject constructor(
    private val agencyRepository: AgencyRepository
) {
    operator fun invoke(): List<Agency> =
        agencyRepository.getAllAgencies()
            .sortedBy { it.name }
}
