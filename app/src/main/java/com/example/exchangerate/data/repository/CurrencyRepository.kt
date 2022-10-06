package com.example.exchangerate.data.repository

import android.util.Log
import com.example.exchangerate.framework.cache.CurrencyCache
import com.example.exchangerate.framework.database.dao.CurrencyDao
import com.example.exchangerate.framework.database.model.CurrencyNameEntity
import com.example.exchangerate.framework.database.model.CurrencyRateEntity
import com.example.exchangerate.framework.network.api.CurrencyApi
import com.example.exchangerate.model.Currency
import com.example.exchangerate.util.SyncUtil
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CurrencyRepository @Inject constructor(
    private val currencyApi: CurrencyApi, private val currencyDao: CurrencyDao
) {
    suspend fun syncCurrencyData() {
        if (SyncUtil.isCurrencyDataAvailableToSync(CurrencyCache.lastSyncTimeStamp?.toLong())) {
            syncCurrencyNamesData()
            syncCurrencyRatesData()
        }
    }

    fun getCurrencyData(): Flow<List<Currency>> {
        return currencyDao.getAllCurrencies()
    }

    private suspend fun syncCurrencyNamesData() {
        try {
            val networkResult = currencyApi.getCurrencyNames()

            currencyDao.deleteCurrencyNamesTable()

            currencyDao.insertAllCurrencyNames(networkResult.map {
                CurrencyNameEntity(
                    id = it.key, name = it.value
                )
            })
        } catch (e: Exception) {
            Log.e(null, e.message, e.cause)
        }
    }

    private suspend fun syncCurrencyRatesData() {
        try {
            val networkResult = currencyApi.getCurrencyRates()

            currencyDao.deleteCurrencyRatesTable()

            currencyDao.insertAllCurrencyRates(networkResult.rates.map {
                CurrencyRateEntity(
                    id = it.key, rateVsUsd = it.value
                )
            })

            CurrencyCache.updateLastSyncTimeStamp(networkResult.timestamp)
        } catch (e: Exception) {
            Log.e(null, e.message, e.cause)
        }
    }
}