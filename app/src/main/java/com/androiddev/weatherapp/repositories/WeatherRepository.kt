package com.androiddev.weatherapp.repositories

import com.androiddev.weatherapp.core.database.enities.Weather
import com.androiddev.weatherapp.core.database.AppDatabase
import com.androiddev.weatherapp.core.network.Outcome
import com.androiddev.weatherapp.core.network.Response
import com.androiddev.weatherapp.core.network.WeatherApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class WeatherRepository(
    private val api: WeatherApi,
    private val db: AppDatabase
): WeatherRepositoryAction {
    override suspend fun getWeatherFromApi(
        city: String
    ): Flow<Response<Weather, Exception>> {
        return flow {
            val weatherResponse = api.getCurrentWeather(city)

            if(weatherResponse.isSuccessful) {
                val body = weatherResponse.body()
                emit(
                    Response(
                        Outcome.Success(
                            Weather(
                                wId = 0,
                                city = body?.name.toString(),
                                location = body?.sys?.country,
                                temperature = body?.main?.temp?.toString(),
                                weather = body?.weather.toString(),
                                description = body?.weather?.get(0)?.description,
                                time = System.currentTimeMillis().toString()
                            )
                        ),
                        null
                    )
                )
            } else {
                emit(
                    Response(
                        null,
                        Outcome.Failure(Exception())
                    )
                )
            }
        }
    }

    override fun getWeatherFromDb(): Flow<Weather>  {
        return flow {
            val weatherData = db.weatherDao.getWeather()
            emit(weatherData)
        }
    }

    override fun saveCurrentWeatherToDb(weather: Weather) {
        db.weatherDao.insertWeather(weather)
    }
}