package com.example.exchangerate.data.repository

import android.util.Log
import com.example.exchangerate.data.datasource.cache.CurrencyCache
import com.example.exchangerate.data.datasource.database.dao.CurrencyDao
import com.example.exchangerate.data.datasource.database.model.CurrencyNameEntity
import com.example.exchangerate.data.datasource.database.model.CurrencyRateEntity
import com.example.exchangerate.data.datasource.network.retrofit.ExchangeRateRetrofit
import com.example.exchangerate.model.Currency
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ExchangeRateRepository @Inject constructor(
    private val exchangeRateRetrofit: ExchangeRateRetrofit,
    private val currencyDao: CurrencyDao
) {
    suspend fun syncData() {
        Log.d("mnf", "kotpref awal: " + CurrencyCache.lastSync)
        if (CurrencyCache.lastSync != null) {
            return
        }

        val networkResult = exchangeRateRetrofit.getCurrencies()

        currencyDao.deleteCurrenciesNameTable()

        currencyDao.insertAllCurrenciesName(networkResult.map {
            CurrencyNameEntity(
                id = it.key,
                name = it.value
            )
        })

        val network2Result = exchangeRateRetrofit.getCurrenciesRate()

        Log.d("mnf", "network timestamp: " + network2Result.timestamp.toString())
        CurrencyCache.lastSync = network2Result.timestamp.toString()
        Log.d("mnf", "kotpref: " + CurrencyCache.lastSync)

        currencyDao.deleteCurrenciesRateTable()

        currencyDao.insertAllCurrenciesRate(network2Result.rates.map {
            CurrencyRateEntity(
                id = it.key,
                rateInUsd = it.value
            )
        })
    }

    fun getExchangeRates(): Flow<List<Currency>> {
        return currencyDao.getAllCurrencies()
    }
}