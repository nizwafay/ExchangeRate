package com.example.exchangerate.data.repository

import com.example.exchangerate.data.datasource.cache.CurrencyCache
import com.example.exchangerate.data.datasource.database.dao.CurrencyDao
import com.example.exchangerate.data.datasource.database.model.CurrencyNameEntity
import com.example.exchangerate.data.datasource.database.model.CurrencyRateEntity
import com.example.exchangerate.data.datasource.network.retrofit.CurrencyRetrofit
import com.example.exchangerate.model.Currency
import com.example.exchangerate.util.SyncUtil
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CurrencyRepository @Inject constructor(
    private val currencyRetrofit: CurrencyRetrofit,
    private val currencyDao: CurrencyDao
) {
    suspend fun syncData() {
        if (SyncUtil.isCurrencyDataAvailableToSync(CurrencyCache.lastSyncTimeStamp?.toLong())) {
            return
        }
        syncCurrencyNamesData()
        syncCurrencyRatesData()
    }

    fun getExchangeRates(): Flow<List<Currency>> {
        return currencyDao.getAllCurrencies()
    }

    private suspend fun syncCurrencyNamesData() {
        val networkResult = currencyRetrofit.getCurrencyNames()

        currencyDao.deleteCurrencyNamesTable()

        currencyDao.insertAllCurrencyNames(networkResult.map {
            CurrencyNameEntity(
                id = it.key,
                name = it.value
            )
        })
    }

    private suspend fun syncCurrencyRatesData() {
        val networkResult = currencyRetrofit.getCurrencyRates()

        currencyDao.deleteCurrencyRatesTable()

        currencyDao.insertAllCurrencyRates(networkResult.rates.map {
            CurrencyRateEntity(
                id = it.key,
                rateInUsd = it.value
            )
        })
    }
}