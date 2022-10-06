package com.example.exchangerate.data.datasource.cache

import com.chibatching.kotpref.KotprefModel

object CurrencyCache : KotprefModel() {
    var lastSync by nullableStringPref()
}