package com.example.exchangerate.data.repository

import com.example.exchangerate.data.datasource.database.dao.CurrencyDao
import com.example.exchangerate.model.Currency
import javax.inject.Inject

class ExchangeRateRepository @Inject constructor(
    private val currencyDao: CurrencyDao
) {
    suspend fun getExchangeRates(): List<Currency> {
        return currencyDao.getAllCurrencies()
    }
}