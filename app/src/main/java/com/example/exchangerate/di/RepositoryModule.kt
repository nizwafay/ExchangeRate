package com.example.exchangerate.di

import com.example.exchangerate.data.repository.CurrencyRepository
import com.example.exchangerate.framework.database.dao.CurrencyDao
import com.example.exchangerate.framework.network.api.CurrencyApi
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
    fun provideCurrencyRepository(
        currencyApi: CurrencyApi, currencyDao: CurrencyDao
    ): CurrencyRepository {
        return CurrencyRepository(
            currencyApi = currencyApi, currencyDao = currencyDao
        )
    }
}