package com.zenika.zenilunch

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class RestaurantUIModel(
    val name: String,
    val type: String,
    val price: String
) : Parcelable