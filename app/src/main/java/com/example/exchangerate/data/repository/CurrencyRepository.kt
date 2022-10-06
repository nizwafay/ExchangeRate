package com.example.exchangerate.data.repository

import com.example.exchangerate.data.datasource.remote.CurrencyRemoteDataSource
import com.example.exchangerate.framework.cache.CurrencyCache
import com.example.exchangerate.framework.database.dao.CurrencyDao
import com.example.exchangerate.framework.database.model.CurrencyNameEntity
import com.example.exchangerate.framework.database.model.CurrencyRateEntity
import com.example.exchangerate.model.Currency
import com.example.exchangerate.util.SyncUtil
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CurrencyRepository @Inject constructor(
    private val currencyRemoteDataSource: CurrencyRemoteDataSource,
    private val currencyDao: CurrencyDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend fun syncCurrencyData() {
        withContext(ioDispatcher) {
            if (SyncUtil.isCurrencyDataAvailableToSync(CurrencyCache.lastSyncTimeStamp?.toLong())) {
                syncCurrencyNamesData()
                syncCurrencyRatesData()
            }
        }
    }

    fun getCurrencyData(): Flow<List<Currency>> {
        return currencyDao.getAllCurrencies()
    }

    private suspend fun syncCurrencyNamesData() {
        val networkResult = currencyRemoteDataSource.getCurrencyNames()

        currencyDao.deleteCurrencyNamesTable()

        currencyDao.insertAllCurrencyNames(networkResult.map {
            CurrencyNameEntity(
                id = it.key, name = it.value
            )
        })
    }

    private suspend fun syncCurrencyRatesData() {
        val networkResult = currencyRemoteDataSource.getCurrencyRates() ?: return

        currencyDao.deleteCurrencyRatesTable()

        currencyDao.insertAllCurrencyRates(networkResult.rates.map {
            CurrencyRateEntity(
                id = it.key, rateVsUsd = it.value
            )
        })

        CurrencyCache.updateLastSyncTimeStamp(networkResult.updatedAt)
    }
}