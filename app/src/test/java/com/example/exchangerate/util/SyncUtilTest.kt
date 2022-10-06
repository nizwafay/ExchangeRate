package com.example.exchangerate.util

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class SyncUtilTest {
    @Test
    fun isCurrencyDataAvailableToSync_null_returnTrue() {
        val input = null

        val result = SyncUtil.isCurrencyDataAvailableToSync(input)

        assertThat(result).isTrue()
    }

    @Test
    fun isCurrencyDataAvailableToSync_inInterval_returnFalse() {
        val input =
            DateUtil.getCurrentTimestamp() - (SyncUtil.MIN_INTERVAL_TO_SYNC_CURRENCY_DATA / 2)

        val result = SyncUtil.isCurrencyDataAvailableToSync(input)

        assertThat(result).isFalse()
    }

    @Test
    fun isCurrencyDataAvailableToSync_outOfInterval_returnTrue() {
        val input = DateUtil.getCurrentTimestamp() - SyncUtil.MIN_INTERVAL_TO_SYNC_CURRENCY_DATA

        val result = SyncUtil.isCurrencyDataAvailableToSync(input)

        assertThat(result).isTrue()
    }

    @Test
    fun isCurrencyDataAvailableToSync_beforeLastSync_returnFalse() {
        val input = DateUtil.getCurrentTimestamp() + 1

        val result = SyncUtil.isCurrencyDataAvailableToSync(input)

        assertThat(result).isFalse()
    }
}