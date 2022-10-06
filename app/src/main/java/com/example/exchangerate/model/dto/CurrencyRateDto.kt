package com.example.exchangerate.model.dto

import com.example.exchangerate.framework.network.model.CurrencyRatesResponse
import com.example.exchangerate.model.CurrencyRate

object CurrencyRateDto {
    fun CurrencyRatesResponse.asCurrencyRateDomainModel(): CurrencyRate {
        return CurrencyRate(
            updatedAt = timestamp,
            rates = rates
        )
    }
}