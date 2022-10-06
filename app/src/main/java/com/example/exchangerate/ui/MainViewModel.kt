package com.example.exchangerate.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.exchangerate.data.repository.ExchangeRateRepository
import com.example.exchangerate.model.Currency
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val exchangeRateRepository: ExchangeRateRepository
) : ViewModel() {
    private val _amount = MutableStateFlow<Float?>(null)
    val amount: StateFlow<Float?>
        get() = _amount

    private val _baseCurrency = MutableStateFlow<Currency?>(null)
    val baseCurrency: StateFlow<Currency?>
        get() = _baseCurrency

    val currencyList: Flow<List<Currency>> = exchangeRateRepository.getExchangeRates()

    val currencyExchangeResult: Flow<List<Currency>> = combine(amount, baseCurrency, currencyList) { a, b, c ->
        c.map {
            Currency(
                id = it.id,
                name = it.name,
                rateInUsd = if (a == null || b == null) 0.0 else a * (it.rateInUsd / b.rateInUsd)
            )
        }
    }

    init {
        syncData()
    }

    fun onAmountChanged(amount: Float?) {
        _amount.update { amount }
    }

    fun onBaseCurrencyChanged(currencyString: String) {
        viewModelScope.launch {
            _baseCurrency.update {
                currencyList.first().firstOrNull {
                    it.id == currencyString
                }
            }
        }
    }

    fun syncData() {
        viewModelScope.launch {
            exchangeRateRepository.syncData()
        }
    }

//    fun getDbData() {
//        viewModelScope.launch {
//            exchangeRateRepository.getExchangeRates()
//        }
//    }
}