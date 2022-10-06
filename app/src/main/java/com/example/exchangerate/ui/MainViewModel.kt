package com.example.exchangerate.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exchangerate.data.repository.CurrencyRepository
import com.example.exchangerate.model.Currency
import com.example.exchangerate.ui.adapter.CurrencyExchangeResult
import com.example.exchangerate.util.CurrencyUtil.rateIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val currencyRepository: CurrencyRepository
) : ViewModel() {
    private val amount = MutableStateFlow<Double?>(null)
    private val baseCurrency = MutableStateFlow<Currency?>(null)

    val currencyList: Flow<List<Currency>> = currencyRepository.getCurrencyData()

    val currencyExchangeResult: Flow<List<CurrencyExchangeResult>> =
        combine(amount, baseCurrency, currencyList) { _amount, _baseCurrency, _currencyList ->
            _currencyList.map {
                it.rateIn(_baseCurrency)
            }.map {
                CurrencyExchangeResult(
                    id = it.id,
                    name = it.name,
                    exchangeResult = _amount?.times(it.exchangeResult) ?: 0.0
                )
            }
        }

    init {
        syncData()
    }

    fun onAmountChanged(amount: Double?) {
        this.amount.update { amount }
    }

    fun onBaseCurrencyChanged(currencyString: String) {
        viewModelScope.launch {
            baseCurrency.update {
                currencyList.first().firstOrNull {
                    it.id == currencyString
                }
            }
        }
    }

    private fun syncData() {
        viewModelScope.launch {
            currencyRepository.syncCurrencyData()
        }
    }
}