package com.androiddev.weatherapp.core.network

sealed class Outcome<T> {
    data class Success<T>(val data: T) : Outcome<T>()
    data class Failure<T>(val e: Throwable) : Outcome<T>()
}