package com.example.exchangerate.util

object SyncUtil {
    // in seconds
    const val MIN_INTERVAL_TO_SYNC_CURRENCY_DATA = 1800

    fun isCurrencyDataAvailableToSync(lastSync: Long?): Boolean {
        return if (lastSync == null) true
        else DateUtil.getCurrentTimestamp() - lastSync >= MIN_INTERVAL_TO_SYNC_CURRENCY_DATA
    }
}