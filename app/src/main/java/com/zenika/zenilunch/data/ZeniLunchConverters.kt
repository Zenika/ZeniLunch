package com.zenika.zenilunch.data

import androidx.room.TypeConverter
import java.util.Date

object ZeniLunchConverters {

    @TypeConverter
    fun toDate(dateLong: Long?): Date? {
        return dateLong?.let { Date(it) }
    }

    @TypeConverter
    fun fromDate(date: Date?): Long? {
        return date?.time
    }
}
