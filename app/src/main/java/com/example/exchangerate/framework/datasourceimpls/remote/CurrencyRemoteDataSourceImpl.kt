package com.example.exchangerate.framework.datasourceimpls.remote

import com.example.exchangerate.data.datasource.remote.CurrencyRemoteDataSource
import com.example.exchangerate.framework.network.api.CurrencyApi
import com.example.exchangerate.model.CurrencyRate
import com.example.exchangerate.model.dto.CurrencyRateDto.asCurrencyRateDomainModel

class CurrencyRemoteDataSourceImpl(private val currencyApi: CurrencyApi) :
    CurrencyRemoteDataSource {
    override suspend fun getCurrencyNames(): Map<String, String> {
        return currencyApi.getCurrencyNames()
    }

    override suspend fun getCurrencyRates(): CurrencyRate {
        return currencyApi.getCurrencyRates().asCurrencyRateDomainModel()
    }
}