package com.androiddev.weatherapp.core.di

import android.app.Application
import androidx.room.Room
import com.androiddev.weatherapp.core.database.AppDatabase
import com.androiddev.weatherapp.core.network.WeatherApi
import com.androiddev.weatherapp.core.network.baseUrl
import com.androiddev.weatherapp.core.utils.AppDispatcherProvider
import com.androiddev.weatherapp.core.utils.DispatcherProvider
import com.androiddev.weatherapp.repositories.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideWeatherApi(): WeatherApi {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(WeatherApi::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(
            app.applicationContext,
            AppDatabase::class.java, "app-database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideWeatherRepository(api: WeatherApi, db: AppDatabase): WeatherRepository {
        return WeatherRepository(api, db)
    }

    @Provides
    @Singleton
    fun provideDispatchers(): DispatcherProvider {
        return AppDispatcherProvider()
    }
}