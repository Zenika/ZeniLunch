package com.zenika.zenilunch

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

fun getRestaurantsFromData(context: Context, fileName: String): String? {
    val jsonString: String
    try {
        jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
    } catch (ioException: IOException) {
        ioException.printStackTrace()
        return null
    }
    return jsonString
}

@Composable
fun getRestaurants(): List<RestaurantUIModel> {
    val context = LocalContext.current
    val jsonFileString = getRestaurantsFromData(context, "restaurants.json")
    val gson = Gson()
    val listRestaurantType = object : TypeToken<List<RestaurantUIModel>>() {}.type
    return gson.fromJson(jsonFileString, listRestaurantType)
}