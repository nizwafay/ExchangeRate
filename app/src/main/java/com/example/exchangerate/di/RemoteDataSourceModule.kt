package com.example.exchangerate.di

import com.example.exchangerate.data.datasource.remote.CurrencyRemoteDataSource
import com.example.exchangerate.framework.datasourceimpls.remote.CurrencyRemoteDataSourceImpl
import com.example.exchangerate.framework.network.api.CurrencyApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteDataSourceModule {
    @Singleton
    @Provides
    fun provideCurrencyRemoteDataSource(currencyApi: CurrencyApi): CurrencyRemoteDataSource {
        return CurrencyRemoteDataSourceImpl(currencyApi = currencyApi)
    }
}