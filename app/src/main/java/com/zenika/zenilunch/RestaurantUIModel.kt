package com.zenika.zenilunch

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class RestaurantUIModel(
    val name: String,
    val type: String,
    val price: String,
    val vegetarian: Boolean,
    val vegan: Boolean,
    val latitude: Double,
    val longitude: Double
) : Parcelable
