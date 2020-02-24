package com.khs.metaweather.service

import com.khs.metaweather.model.MetaWeather
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface APIService {
    @GET("/api/location/{woeid}/")
    fun getWeatherInfoOfLocationOfDay(@Path("woeid") location:String): Call<MetaWeather>
}