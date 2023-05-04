package com.zenika.zenilunch.repository

import android.content.SharedPreferences
import androidx.core.content.edit
import com.zenika.zenilunch.agency.model.Agency
import com.zenika.zenilunch.agency.model.LatLng
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

private const val LOGO_BASE_URL = "https://images.prismic.io/zenika-website/"
private const val LOGO_QUERY = "?auto=compress,format&rect=0,0,210,160&w=210&h=160"
private const val LYON_LOGO = "66611bc6-db32-403b-8149-3dcf77e805a8_lyon.png"
private const val CLERMONT_LOGO = "c9fadba9-7234-4f62-972d-336964bdbb6b_clermont-ferrand-petite.png"

class AgencyRepository @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {
    private val agencies = listOf(
        Agency(
            "lyon",
            "Lyon",
            "$LOGO_BASE_URL$LYON_LOGO$LOGO_QUERY",
            "lyon/restaurants.json",
            LatLng(
                lat = 45.766752337134754,
                lng = 4.858952442403011
            )
        ),
        Agency(
            "clermont-ferrand",
            "Clermont-Ferrand",
            "$LOGO_BASE_URL$CLERMONT_LOGO$LOGO_QUERY",
            "clermont-ferrand/restaurants.json",
            LatLng(
                lat = 45.75909302686358,
                lng = 3.130090039878613
            )
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
