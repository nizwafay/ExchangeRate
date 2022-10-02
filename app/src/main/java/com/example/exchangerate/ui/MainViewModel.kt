package com.example.exchangerate.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exchangerate.data.repository.ExchangeRateRepository
import com.example.exchangerate.model.Currency
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val exchangeRateRepository: ExchangeRateRepository
) : ViewModel() {
    private val _data = MutableLiveData<List<Currency>>()
    val data: LiveData<List<Currency>>
        get() = _data

    init {
        getData()
    }

    fun getData() {
        viewModelScope.launch {
            _data.value = exchangeRateRepository.getExchangeRates()
        }
    }
}