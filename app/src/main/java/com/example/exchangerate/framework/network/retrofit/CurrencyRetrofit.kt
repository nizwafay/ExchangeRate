package com.example.exchangerate.framework.network.retrofit

import com.example.exchangerate.data.datasourceimpls.network.model.CurrencyRatesResponse
import com.example.exchangerate.util.ApiConstants.Companion.APP_ID
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyRetrofit {
    @GET("currencies.json")
    suspend fun getCurrencyNames(): Map<String, String>

    @GET("latest.json")
    suspend fun getCurrencyRates(
        @Query("app_id")
        appId: String = APP_ID
    ): CurrencyRatesResponse
}