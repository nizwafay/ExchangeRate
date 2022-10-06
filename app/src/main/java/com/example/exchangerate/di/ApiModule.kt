package com.example.exchangerate.di

import android.content.Context
import com.example.exchangerate.framework.network.RetrofitInstance
import com.example.exchangerate.framework.network.api.CurrencyApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Singleton
    @Provides
    fun provideRetrofitInstance(@ApplicationContext context: Context): Retrofit {
        return RetrofitInstance.createInstance(context)
    }

    @Singleton
    @Provides
    fun provideCurrencyApi(retrofitInstance: Retrofit): CurrencyApi {
        return retrofitInstance.create(CurrencyApi::class.java)
    }
}