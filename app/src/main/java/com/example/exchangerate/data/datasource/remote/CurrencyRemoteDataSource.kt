package com.example.exchangerate.data.datasource.remote

import com.example.exchangerate.model.CurrencyRate

interface CurrencyRemoteDataSource {
    suspend fun getCurrencyNames(): Map<String, String>

    suspend fun getCurrencyRates(): CurrencyRate
}