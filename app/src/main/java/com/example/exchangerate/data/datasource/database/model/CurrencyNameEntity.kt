package com.example.exchangerate.data.datasource.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currency_names_table")
data class CurrencyNameEntity(
    @PrimaryKey
    val id: String,
    val name: String
)