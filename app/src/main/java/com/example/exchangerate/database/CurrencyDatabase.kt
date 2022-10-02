package com.example.exchangerate.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.exchangerate.database.dao.CurrencyDao
import com.example.exchangerate.database.model.CurrencyNameEntity
import com.example.exchangerate.database.model.CurrencyRateEntity

@Database(
    entities = [CurrencyNameEntity::class, CurrencyRateEntity::class],
    version = 1,
    exportSchema = false
)
abstract class CurrencyDatabase : RoomDatabase() {
    abstract fun currencyDao(): CurrencyDao

    companion object {
        @Volatile
        private var instance: CurrencyDatabase? = null

        fun getInstance(context: Context): CurrencyDatabase = instance ?: synchronized(this) {
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            CurrencyDatabase::class.java,
            "currency_database.db"
        ).build()
    }
}