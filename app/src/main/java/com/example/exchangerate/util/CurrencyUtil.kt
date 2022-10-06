package com.example.exchangerate.util

import com.example.exchangerate.model.Currency
import com.example.exchangerate.ui.adapter.CurrencyExchangeResult

object CurrencyUtil {
    fun Currency.rateIn(currency: Currency?): CurrencyExchangeResult {
        return CurrencyExchangeResult(
            id = id,
            name = name,
            exchangeResult = if (currency == null) 0.0 else rateInUsd / currency.rateInUsd
        )
    }
}