package com.example.exchangerate.data.datasource.network.retrofit

import com.example.exchangerate.data.datasource.network.model.CurrenciesRateResponse
import com.example.exchangerate.util.Constants.Companion.APP_ID
import retrofit2.http.GET
import retrofit2.http.Query

interface ExchangeRateRetrofit {
    @GET("currencies.json")
    suspend fun getCurrencies(): Map<String, String>

    @GET("latest.json")
    suspend fun getCurrenciesRate(
        @Query("app_id")
        appId: String = APP_ID
    ): CurrenciesRateResponse
}