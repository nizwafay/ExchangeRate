package com.example.exchangerate.framework.datasourceimpls.remote

import com.example.exchangerate.framework.network.api.CurrencyApi
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import org.junit.Before
import org.junit.Test

class CurrencyRemoteDataSourceImplTest : BaseRemoteDataSourceTest() {
    private lateinit var currencyApi: CurrencyApi

    @Before
    override fun setUp() {
        super.setUp()
        currencyApi = retrofitInstance.create(CurrencyApi::class.java)
    }

    @Test
    fun getCurrencyNames_returnRealData() {
        enqueueMockResponse("currencies.json")

        runBlocking {
            val actual = currencyApi.getCurrencyNames()

            server.takeRequest()
            Truth.assertThat(actual).isNotEmpty()
            Truth.assertThat(actual).containsKey("USD")
        }
    }

    @Test
    fun getCurrencyNames_returnEmptyData() {
        enqueueMockResponse("empty.json")

        runBlocking {
            val actual = currencyApi.getCurrencyNames()

            server.takeRequest()
            Truth.assertThat(actual).isEmpty()
        }
    }

    @Test
    fun getCurrencyRates_invalidAppId_returnNull() {
        server.enqueue(MockResponse().setResponseCode(403))

        runBlocking {
            val actual = currencyApi.getCurrencyRates(appId = null)

            val expectedStatusCode = 403

            server.takeRequest()
            Truth.assertThat(actual.code()).isEqualTo(expectedStatusCode)
            Truth.assertThat(actual.body()).isNull()
        }
    }

    @Test
    fun getCurrencyNames_validAppId_returnRealData() {
        enqueueMockResponse("latest.json")

        runBlocking {
            val validAppId = "valid_app_id"
            val actual = currencyApi.getCurrencyRates(appId = validAppId)

            server.takeRequest()
            Truth.assertThat(actual.body()).isNotNull()
            Truth.assertThat(actual.body()!!.timestamp).isNotNull()
            Truth.assertThat(actual.body()!!.rates).containsKey("USD")
        }
    }

}