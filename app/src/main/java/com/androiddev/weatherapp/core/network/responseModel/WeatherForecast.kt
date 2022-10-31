package com.androiddev.weatherapp.core.network.responseModel

import com.example.example.List
import com.google.gson.annotations.SerializedName

data class WeatherForecast (
  @SerializedName("cod"     ) var cod     : String?         = null,
  @SerializedName("message" ) var message : Int?            = null,
  @SerializedName("cnt"     ) var cnt     : Int?            = null,
  @SerializedName("list"    ) var list    : ArrayList<List> = arrayListOf(),
  @SerializedName("city"    ) var city    : City?           = City()
)