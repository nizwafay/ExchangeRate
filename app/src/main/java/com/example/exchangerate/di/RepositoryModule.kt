package com.example.exchangerate.di

import com.example.exchangerate.data.datasource.remote.CurrencyRemoteDataSource
import com.example.exchangerate.data.repository.CurrencyRepository
import com.example.exchangerate.framework.database.dao.CurrencyDao
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
        currencyRemoteDataSource: CurrencyRemoteDataSource, currencyDao: CurrencyDao
    ): CurrencyRepository {
        return CurrencyRepository(
            currencyRemoteDataSource = currencyRemoteDataSource, currencyDao = currencyDao
        )
    }
}