package com.example.exchangerate

import okio.buffer
import okio.source

object TestUtil {
    fun readFile(fileName: String): String? {
        return javaClass.classLoader?.let {
            val inputStream = it.getResourceAsStream(fileName)
            val source = inputStream.source().buffer()
            source.readString(Charsets.UTF_8)
        }
    }
}