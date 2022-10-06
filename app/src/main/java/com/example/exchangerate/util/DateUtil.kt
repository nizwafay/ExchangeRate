package com.example.exchangerate.util

object DateUtil {
    fun getCurrentTimestamp(): Long {
        return System.currentTimeMillis() / 1000
    }
}