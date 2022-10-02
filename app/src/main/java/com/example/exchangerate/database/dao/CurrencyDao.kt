package com.example.exchangerate.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.exchangerate.database.model.CurrencyNameEntity
import com.example.exchangerate.database.model.CurrencyRateEntity
import com.example.exchangerate.model.Currency

@Dao
interface CurrencyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCurrenciesName(currenciesName: List<CurrencyNameEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCurrenciesRate(currenciesRate: List<CurrencyRateEntity>)

    @Query(
        "SELECT currencies_name_table.id AS id," +
                "currencies_name_table.name AS name," +
                "currencies_rate_table.rateInUsd AS rateInUsd" +
                "FROM currencies_name_table, currencies_rate_table" +
                "WHERE currencies_name_table.id = currencies_rate_table.id"
    )
    suspend fun getAllCurrencies(): List<Currency>

    @Query("SELECT * FROM currencies_name_table")
    suspend fun test()

    @Query("DELETE FROM currencies_name_table")
    suspend fun deleteCurrenciesNameTable()

    @Query("DELETE FROM currencies_rate_table")
    suspend fun deleteCurrenciesRateTable()
}