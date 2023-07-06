package com.android.main.data

import com.example.main.data.Network
import okhttp3.Interceptor
import okhttp3.Response

class AuthTokenInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val newHeaders = originalRequest.headers().newBuilder().add("X-API-KEY", Network.API_TOKEN).build()

        return chain.proceed(originalRequest.newBuilder().headers(newHeaders).build())
    }
}