package com.example.exchangerate.framework.network.model

import com.google.gson.annotations.SerializedName

data class CurrencyRatesResponse(
    @SerializedName("disclaimer")
    val disclaimer: String = "",
    @SerializedName("license")
    val license: String = "",
    @SerializedName("timestamp")
    val timestamp: Long = 0L,
    @SerializedName("base")
    val base: String = "",
    @SerializedName("rates")
    val rates: Map<String, Double>
)