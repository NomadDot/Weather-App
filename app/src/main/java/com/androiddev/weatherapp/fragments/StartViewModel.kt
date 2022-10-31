package com.androiddev.weatherapp.fragments

import com.androiddev.weatherapp.core.database.enities.Weather
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androiddev.weatherapp.core.utils.DispatcherProvider
import com.androiddev.weatherapp.repositories.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class StartViewModel @Inject constructor(
    private val repository: WeatherRepository,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {

    private var _weatherLD = MutableLiveData<Weather?>()
    val weatherData: LiveData<Weather?> get() = _weatherLD

    fun getWeatherFromApi(city: String) {
        viewModelScope.launch(dispatcherProvider.default) {
            repository.getWeatherFromApi(city)
                .collect {
                    when {
                        it.isSuccess -> {
                            it.data?.let { response ->
                                repository.saveCurrentWeatherToDb(response)
                                withContext(dispatcherProvider.main) {
                                    _weatherLD.value = response
                                }
                            }
                        }
                        it.isFailure -> {
                            print(it.exception)
                        }
                    }
                }
        }
    }

    fun getWeatherFromDb() {
        viewModelScope.launch(dispatcherProvider.default) {
            repository.getWeatherFromDb()
                .collect {
                    withContext(dispatcherProvider.main) {
                        _weatherLD.value = it
                    }
                }
        }
    }
}