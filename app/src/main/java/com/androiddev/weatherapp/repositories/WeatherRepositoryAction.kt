package com.androiddev.weatherapp.repositories

import com.androiddev.weatherapp.core.database.enities.Weather
import com.androiddev.weatherapp.core.network.Response
import kotlinx.coroutines.flow.Flow

interface WeatherRepositoryAction {
    suspend fun getWeatherFromApi(city: String): Flow<Response<Weather, Exception>>
    fun getWeatherFromDb(): Flow<Weather>

    fun saveCurrentWeatherToDb(weather: Weather)
}