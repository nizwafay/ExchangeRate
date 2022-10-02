package com.example.exchangerate.di

import android.content.Context
import com.example.exchangerate.data.datasource.database.CurrencyDatabase
import com.example.exchangerate.data.datasource.database.dao.CurrencyDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Singleton
    @Provides
    fun provideCurrencyDao(@ApplicationContext context: Context): CurrencyDao {
        return CurrencyDatabase.getInstance(context).currencyDao()
    }
}