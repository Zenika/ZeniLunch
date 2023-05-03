package com.zenika.zenilunch.repository

import android.content.SharedPreferences
import androidx.core.content.edit
import com.zenika.zenilunch.agency.model.Agency
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AgencyRepository @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {
    private val agencies = listOf(
        Agency(
            "lyon",
            "Lyon",
            "https://images.prismic.io/zenika-website/66611bc6-db32-403b-8149-3dcf77e805a8_lyon.png?auto=compress,format&rect=0,0,210,160&w=210&h=160",
            "main/restaurants.json"
        ),
        Agency(
            "clermont-ferrand",
            "Clermont-Ferrand",
            "https://images.prismic.io/zenika-website/c9fadba9-7234-4f62-972d-336964bdbb6b_clermont-ferrand-petite.png?auto=compress,format&rect=0,0,210,160&w=210&h=160",
            "main/restaurants.json"
        )
    )

    suspend fun setSelectedAgency(agency: Agency) {
        withContext(Dispatchers.IO) {
            sharedPreferences.edit {
                putString("selectedAgency", agency.id)
            }
        }
    }

    suspend fun getSelectedAgency(): Agency {
        val agencyId = sharedPreferences.getString("selectedAgency", "lyon")!!
        return withContext(Dispatchers.IO) {
            agencies.first { it.id == agencyId }
        }
    }

    fun getAllAgencies(): List<Agency> {
        return agencies
    }

    suspend fun hasSelectedAgency(): Boolean {
        return withContext(Dispatchers.IO) {
            sharedPreferences.contains("selectedAgency")
        }
    }
}
