package com.example.exchangerate.util

import com.example.exchangerate.model.Currency
import com.example.exchangerate.ui.adapter.CurrencyExchangeResult
import com.example.exchangerate.util.CurrencyUtil.rateIn
import com.google.common.truth.Truth
import org.junit.Test

class CurrencyUtilTest {
    private val sgdCurrency = Currency(
        id = "SGD",
        name = "Singapore Dollar",
        rateVsUsd = 1.427243
    )

    private val idrCurrency = Currency(
        id = "IDR",
        name = "Indonesian Rupiah",
        rateVsUsd = 15222.9
    )

    @Test
    fun rateIn_inputNullInIdr_returnSgdWithOneExchangeResult() {
        val result = null.rateIn(idrCurrency)

        val expectedResult = CurrencyExchangeResult(
            id = idrCurrency.id,
            name = idrCurrency.name,
            exchangeResult = 1.0
        )
        Truth.assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    fun rateIn_inputSgdInIdr_returnIdrWithOneSgdInIdrValueExchangeResult() {
        val result = sgdCurrency.rateIn(idrCurrency)

        val expectedResult = CurrencyExchangeResult(
            id = idrCurrency.id,
            name = idrCurrency.name,
            exchangeResult = 10665.948265291894
        )
        Truth.assertThat(result).isEqualTo(expectedResult)
    }
}