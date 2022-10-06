package com.example.exchangerate.model

data class CurrencyRate(
    // in timestamp
    val updatedAt: Long,
    val rates: Map<String, Double>
)
