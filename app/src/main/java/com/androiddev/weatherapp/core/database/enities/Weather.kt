package com.androiddev.weatherapp.core.database.enities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Weather(
    @PrimaryKey(autoGenerate = true) val wId: Int = 0,
    @ColumnInfo(name = "city") val city: String?,
    @ColumnInfo(name = "country") val location: String?,
    @ColumnInfo(name = "temperature") val temperature: String?,
    @ColumnInfo(name = "weather") val weather: String?,
    @ColumnInfo(name = "description") val description: String? = null,
    @ColumnInfo(name = "time") val time: String?,
)