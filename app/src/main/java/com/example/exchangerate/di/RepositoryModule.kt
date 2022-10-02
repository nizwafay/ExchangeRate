package com.example.exchangerate.di

import com.example.exchangerate.data.datasource.database.dao.CurrencyDao
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
    fun provideUserRepository(currencyDao: CurrencyDao): ExchangeRateRepository {
        return ExchangeRateRepository(currencyDao = currencyDao)
    }
}