package com.khs.metaweather

import android.app.Application
import com.facebook.stetho.Stetho
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.khs.metaweather.service.APIService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherApplication : Application(){

    val baseUri = "https://www.metaweather.com/"
    lateinit var service: APIService
    var client:OkHttpClient?=null

    override fun onCreate() {
        super.onCreate()
    setupStethoClient()
    setupRetrofit()
    }

    private fun setupStethoClient() {
        Stetho.initializeWithDefaults(this)
        val httpClient  = OkHttpClient.Builder()
        httpClient.addNetworkInterceptor(StethoInterceptor())
        client = httpClient.build()
    }

    private fun setupRetrofit() {
        val retorfit = Retrofit.Builder()
            .baseUrl(baseUri)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        service = retorfit.create(APIService::class.java)
    }

    fun getAPIService(): APIService? = service
}