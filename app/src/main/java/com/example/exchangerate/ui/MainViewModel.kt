package com.example.exchangerate.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exchangerate.data.repository.ExchangeRateRepository
import com.example.exchangerate.model.Currency
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val exchangeRateRepository: ExchangeRateRepository
) : ViewModel() {

    init {
        syncData()
        getData()
    }

    fun syncData() {
        viewModelScope.launch {
            exchangeRateRepository.syncData()
        }
    }

    fun getData(): Flow<List<Currency>> {
        return exchangeRateRepository.getExchangeRates()
    }

//    fun getDbData() {
//        viewModelScope.launch {
//            exchangeRateRepository.getExchangeRates()
//        }
//    }
}