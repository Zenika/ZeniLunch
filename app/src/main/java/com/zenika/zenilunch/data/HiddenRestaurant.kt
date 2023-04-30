package com.zenika.zenilunch.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "HiddenRestaurant")
data class HiddenRestaurant(
    @PrimaryKey
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "hiddenAt")
    val hiddenAt: Date
)
