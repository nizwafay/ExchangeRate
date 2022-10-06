package com.example.exchangerate.di

import com.example.exchangerate.data.datasourceimpls.database.dao.CurrencyDao
import com.example.exchangerate.data.datasourceimpls.network.retrofit.CurrencyRetrofit
import com.example.exchangerate.data.repository.CurrencyRepository
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
        currencyRetrofit: CurrencyRetrofit, currencyDao: CurrencyDao
    ): CurrencyRepository {
        return CurrencyRepository(
            currencyRetrofit = currencyRetrofit, currencyDao = currencyDao
        )
    }
}