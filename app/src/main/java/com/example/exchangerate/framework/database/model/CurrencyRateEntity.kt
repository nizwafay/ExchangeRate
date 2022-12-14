package com.example.exchangerate.framework.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currency_rates_table")
data class CurrencyRateEntity(
    @PrimaryKey
    val id: String,
    @ColumnInfo(name = "rate_vs_usd")
    val rateVsUsd: Double
)