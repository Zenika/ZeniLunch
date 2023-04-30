package com.zenika.zenilunch.data

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface RestaurantDao {
    @Query("SELECT * FROM HiddenRestaurant")
    suspend fun getHiddenRestaurants(): List<HiddenRestaurant>

    @Upsert(HiddenRestaurant::class)
    suspend fun upsertRestaurant(restaurant: HiddenRestaurant)
}

