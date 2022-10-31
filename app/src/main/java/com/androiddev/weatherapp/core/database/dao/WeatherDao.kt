package com.androiddev.weatherapp.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.androiddev.weatherapp.core.database.enities.Weather

@Dao
interface WeatherDao {
    @Query("SELECT * FROM weather")
    fun getWeather(): Weather

    @Insert
    fun insertWeather(vararg weather: Weather)

    @Delete
    fun deleteWeather(weather: Weather)
}