package com.example.exchangerate.di

import android.content.Context
import com.example.exchangerate.data.datasource.network.RetrofitInstance
import com.example.exchangerate.data.datasource.network.retrofit.ExchangeRateRetrofit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    @Singleton
    @Provides
    fun provideRetrofitInstance(@ApplicationContext context: Context): Retrofit {
        return RetrofitInstance.createInstance(context)
    }

    @Singleton
    @Provides
    fun provideUserRetrofit(retrofitInstance: Retrofit): ExchangeRateRetrofit {
        return retrofitInstance.create(ExchangeRateRetrofit::class.java)
    }
}