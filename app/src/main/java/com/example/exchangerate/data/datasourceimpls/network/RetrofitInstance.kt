package com.example.exchangerate.data.datasourceimpls.network

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.exchangerate.util.ApiConstants.Companion.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    companion object {
        fun createInstance(context: Context): Retrofit {
            val client = OkHttpClient.Builder()
                .addInterceptor(ChuckerInterceptor(context))
                .addInterceptor { chain ->
                    val request = chain.request().newBuilder()
                        .build()
                    val response = chain.proceed(request)
                    response
                }
                .build()
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }
    }
}