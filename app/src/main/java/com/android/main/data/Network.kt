package com.example.main.data

import com.android.main.data.AuthTokenInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

object Network {
    const val API_TOKEN = "582189f9-7d33-455f-aae7-d1a5f3f160a0"
    private const val URL = "https://kinopoiskapiunofficial.tech/api/"

    private val okHttpClient = OkHttpClient.Builder()
        .addNetworkInterceptor(AuthTokenInterceptor()).build()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl(URL)
        .client(okHttpClient)
        .build()

    val kinopoiskApi: KinopoiskApi
        get() = retrofit.create()
}