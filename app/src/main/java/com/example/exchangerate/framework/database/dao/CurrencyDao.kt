package com.example.exchangerate.framework.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.exchangerate.framework.database.model.CurrencyNameEntity
import com.example.exchangerate.framework.database.model.CurrencyRateEntity
import com.example.exchangerate.model.Currency
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrencyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCurrencyNames(currencyNames: List<CurrencyNameEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCurrencyRates(currencyRates: List<CurrencyRateEntity>)

    @Query(
        "SELECT currency_names_table.id AS id, " +
                "currency_names_table.name AS name, " +
                "currency_rates_table.rate_vs_usd AS rateVsUsd " +
                "FROM currency_names_table, currency_rates_table " +
                "WHERE currency_names_table.id = currency_rates_table.id"
    )
    fun getAllCurrencies(): Flow<List<Currency>>

    @Query("DELETE FROM currency_names_table")
    suspend fun deleteCurrencyNamesTable()

    @Query("DELETE FROM currency_rates_table")
    suspend fun deleteCurrencyRatesTable()
}