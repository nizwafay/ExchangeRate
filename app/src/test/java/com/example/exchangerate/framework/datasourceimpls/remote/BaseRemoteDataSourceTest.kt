package com.example.exchangerate.framework.datasourceimpls.remote

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
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
        javaClass.classLoader?.let {
            val inputStream = it.getResourceAsStream(fileName)
            val source = inputStream.source().buffer()
            val mockResponse = MockResponse()
            mockResponse.setBody(source.readString(Charsets.UTF_8))
            server.enqueue(mockResponse)
        }
    }

    @After
    fun tearDown() {
        server.shutdown()
    }
}