package com.androiddev.weatherapp.core.network

import com.androiddev.weatherapp.core.network.responseModel.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

const val apiKey = "dfa096a3c3ace225d439ee06a7af9b39"
const val baseUrl = "https://api.openweathermap.org"

 interface WeatherApi {

     @GET("/data/2.5/weather?appid=$apiKey")
     suspend fun getCurrentWeather(
         @Query("q") city: String
     ): Response<WeatherResponse>
}