package com.zenika.zenilunch.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [HiddenRestaurant::class],
    version = 1
)
@TypeConverters(ZeniLunchConverters::class)
abstract class RestaurantDatabase : RoomDatabase() {
    abstract val restaurantDao: RestaurantDao
}
