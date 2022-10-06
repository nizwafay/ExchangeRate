package com.example.exchangerate.data.datasourceimpls.cache

import com.chibatching.kotpref.KotprefModel

object CurrencyCache : KotprefModel() {
    private var _lastSyncTimeStamp by nullableStringPref()
    val lastSyncTimeStamp get() = _lastSyncTimeStamp

    fun updateLastSyncTimeStamp(timestamp: Long) {
        _lastSyncTimeStamp = timestamp.toString()
    }
}