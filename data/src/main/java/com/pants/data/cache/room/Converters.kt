package com.pants.data.cache.room

import androidx.room.TypeConverter
import java.util.*

class Converters {

    @TypeConverter
    fun toDate(value: Long): Date = Date(value)

    @TypeConverter
    fun toTimestamp(date: Date): Long = date.time
}