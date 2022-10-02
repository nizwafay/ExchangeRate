package com.example.exchangerate.data.repository

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
        val networkResult = exchangeRateRetrofit.getCurrencies()

        currencyDao.deleteCurrenciesNameTable()

        currencyDao.insertAllCurrenciesName(networkResult.map {
            CurrencyNameEntity(
                id = it.key,
                name = it.value
            )
        })

        val network2Result = exchangeRateRetrofit.getCurrenciesRate()

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