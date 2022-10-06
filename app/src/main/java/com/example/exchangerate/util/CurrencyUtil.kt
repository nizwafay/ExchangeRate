package com.example.exchangerate.util

import com.example.exchangerate.model.Currency
import com.example.exchangerate.ui.adapter.CurrencyExchangeResult

object CurrencyUtil {
    fun Currency?.rateIn(currency: Currency): CurrencyExchangeResult {
        return CurrencyExchangeResult(
            id = currency.id,
            name = currency.name,
            exchangeResult = if (this == null) 1.0 else currency.rateVsUsd / rateVsUsd
        )
    }
}