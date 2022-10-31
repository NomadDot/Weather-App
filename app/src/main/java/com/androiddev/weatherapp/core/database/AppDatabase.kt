package com.androiddev.weatherapp.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.androiddev.weatherapp.core.database.dao.WeatherDao
import com.androiddev.weatherapp.core.database.enities.Weather

@Database(entities = [Weather::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract val weatherDao: WeatherDao
}