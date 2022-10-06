package com.example.exchangerate.framework.datasourceimpls.remote

import com.example.exchangerate.TestUtil
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

abstract class BaseRemoteDataSourceTest {
    internal lateinit var retrofitInstance: Retrofit
    internal lateinit var server: MockWebServer

    @Before
    open fun setUp() {
        server = MockWebServer()
        retrofitInstance = Retrofit.Builder()
            .baseUrl(server.url(""))//We will use MockWebServers url
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    internal fun enqueueMockResponse(fileName: String) {
        TestUtil.readFile(fileName)?.let {
            val mockResponse = MockResponse()
            mockResponse.setBody(it)
            server.enqueue(mockResponse)
        }
    }

    @After
    fun tearDown() {
        server.shutdown()
    }
}