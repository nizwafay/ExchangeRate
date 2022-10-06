package com.example.exchangerate

import android.app.Application
import com.chibatching.kotpref.Kotpref
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ExchangeRateApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        Kotpref.init(this)
    }
}