package com.zenika.zenilunch.repository

import android.content.SharedPreferences
import androidx.core.content.edit
import com.zenika.zenilunch.ageny.model.Agency
import javax.inject.Inject

class AgencyRepository @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {
    fun setSelectedAgency(agency: Agency) {
        sharedPreferences.edit {
            putString("selectedAgency", agency.id)
        }
    }

    fun getSelectedAgency(): String? {
        return sharedPreferences.getString("selectedAgency", null)
    }

    fun getAllAgencies(): List<Agency> = listOf(
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
}
