package com.example.exchangerate.di

import com.example.exchangerate.data.datasource.database.CurrencyDatabase
import com.example.exchangerate.data.datasource.database.dao.CurrencyDao
import com.example.exchangerate.data.datasource.network.retrofit.ExchangeRateRetrofit
import com.example.exchangerate.data.repository.ExchangeRateRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideUserRepository(exchangeRateRetrofit: ExchangeRateRetrofit, currencyDao: CurrencyDao): ExchangeRateRepository {
        return ExchangeRateRepository(
            exchangeRateRetrofit = exchangeRateRetrofit,
            currencyDao = currencyDao
        )
    }
}