package com.example.exchangerate.data.datasource.network.model
import com.google.gson.annotations.SerializedName


data class CurrenciesRateResponse(
    @SerializedName("disclaimer")
    val disclaimer: String = "",
    @SerializedName("license")
    val license: String = "",
    @SerializedName("timestamp")
    val timestamp: Int = 0,
    @SerializedName("base")
    val base: String = "",
    @SerializedName("rates")
    val rates: Map<String, Double>
)