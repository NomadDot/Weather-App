package com.androiddev.weatherapp.core.network

class Response<T, E>(
    private val success: Outcome.Success<T>? = null,
    private val failure: Outcome.Failure<E>? = null
): ApiResponse<T> {
    override val isSuccess: Boolean  = success != null
    override val isFailure: Boolean = failure != null

    override val data: T? = success?.data
    override val exception: Throwable? = failure?.e
}

interface ApiResponse<out T> {
    val isSuccess: Boolean
    val isFailure: Boolean
    val data: T?
    val exception: Throwable?
}
